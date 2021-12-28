package com.gameUniverse.GameUniverse.requests;

import com.gameUniverse.GameUniverse.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private Roles role;
}
