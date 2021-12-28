package com.gameUniverse.GameUniverse.controllers;

import com.gameUniverse.GameUniverse.requests.NewUserRequest;
import com.gameUniverse.GameUniverse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @GetMapping(value = "/add")
    public String getNewUserForm() {
        return "/users/addUser";
    }

    @PostMapping(value = "/add")
    public ModelAndView addNewUser(
            @Valid @ModelAttribute("request") NewUserRequest request, ModelAndView modelAndView
    ) {
        modelAndView.setViewName("/user/addUser");
        modelAndView.addObject("user", this.userService.addNewUser(request));

        return modelAndView;
    }

    @GetMapping(value = "/all")
    public ModelAndView getUsersList(ModelAndView modelAndView) {
        modelAndView.addObject("list", this.userService.getUsersList());
        modelAndView.setViewName("/users/allUsers");

        return modelAndView;
    }
}
