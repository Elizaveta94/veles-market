package com.velesmarket.web;

import com.velesmarket.domain.UserDto;
import com.velesmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute UserDto user, Model model) {
        UserDto newUser = userService.registrate(user);
        model.addAttribute("user", newUser);
        return "userProfile";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDto user, Model model) {
        UserDto newUser = userService.login(user);
        model.addAttribute("user", newUser);
        return "userProfile";
    }

    @GetMapping("/profile")
    public String profile() {
        return "userProfile";
    }

}
