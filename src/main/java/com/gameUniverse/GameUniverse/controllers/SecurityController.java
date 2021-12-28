package com.gameUniverse.GameUniverse.controllers;

import com.gameUniverse.GameUniverse.requests.NewUserRequest;
import com.gameUniverse.GameUniverse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class SecurityController {
    private final UserService userService;

    @GetMapping(value = "/registration")
    public String getRegistrationForm() {
        return "/registration";
    }

    @PostMapping(value = "/registration")
    public String passData(@ModelAttribute("request") NewUserRequest request) {
        this.userService.addNewUser(request);

        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "/login";
    }
}
