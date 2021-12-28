package com.gameUniverse.GameUniverse.service;


import com.gameUniverse.GameUniverse.entities.User;
import com.gameUniverse.GameUniverse.exeption.BadRequestException;
import com.gameUniverse.GameUniverse.repositories.UserRepository;
import com.gameUniverse.GameUniverse.requests.NewUserRequest;
import com.gameUniverse.GameUniverse.requests.UpdateUserRequest;
import com.gameUniverse.GameUniverse.response.UserInfoResponse;
import com.gameUniverse.GameUniverse.roles.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Scheduler scheduler;

    public UserInfoResponse addNewUser(NewUserRequest request) {
        Set<Roles> roleList = new HashSet<>();
        roleList.add(Roles.ROLE_USER);

        if(request.getRole().equals("ADMIN")) {
            roleList.add(Roles.ROLE_ADMIN);
        }

        this.userRepository.save(new User(
                request.getLogin(),
                request.getEmail(),
                this.passwordEncoder.encode(request.getPassword()),
                roleList
        ));
        this.scheduler.clearCache();

        return new UserInfoResponse(
                request.getLogin(),
                request.getEmail()
        );
    }

    @Cacheable("userCache")
    public List<UserInfoResponse> getUsersList() {
        List<User> list = this.userRepository.findAll();

        return list.stream().map(
                user -> new UserInfoResponse(
                        user.getLogin(),
                        user.getEmail()
                )
        ).collect(Collectors.toList());
    }

    public UserInfoResponse updateUser(UpdateUserRequest request) {
        Optional<User> optional = this.userRepository.findById(request.getId());

        User user = optional.orElseThrow(
                () -> new BadRequestException("No user with such id!", HttpStatus.BAD_REQUEST)
        );

        user.setLogin(request.getLogin());
        user.setEmail(request.getEmail());
        user.setPassword(
                this.passwordEncoder.encode(request.getPassword())
        );
        user.getRoles().add(request.getRole());

        this.userRepository.save(user);
        this.scheduler.clearCache();

        return new UserInfoResponse(
                user.getLogin(),
                user.getEmail()
        );
    }


    public void deleteUser(Long id) {
        this.scheduler.clearCache();
        this.userRepository.deleteById(id);
    }
}
