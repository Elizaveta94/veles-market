package com.velesmarket.web;

import com.velesmarket.domain.UserDto;
import com.velesmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.net.http.HttpClient;
import java.security.Principal;

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
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        UserDto user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "userProfile";
    }

    @GetMapping("/edit")
    public String editProfile(Principal principal, Model model) {
        UserDto user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "editProfile";
    }

    @PostMapping("/edit")
    public String changesSave(@ModelAttribute UserDto user, Model model, Principal principal) {
        UserDto newUser = userService.update(user, principal.getName());
        model.addAttribute("user", newUser);
        return "redirect:userProfile";
    }

}
