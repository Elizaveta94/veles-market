package com.velesmarket.web;

import com.velesmarket.domain.AnnouncementCardDto;
import com.velesmarket.domain.UserDto;
import com.velesmarket.service.AnnouncementService;
import com.velesmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AnnouncementService announcementService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute UserDto user, Model model) {
        UserDto newUser = userService.registrate(user);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/profile";
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
    public String changesSave(@ModelAttribute UserDto user, Principal principal) {
        userService.update(user, principal.getName());
        return "redirect:profile";
    }

    @GetMapping("/user/announcement")
    public String getUserAnnouncement(Principal principal, Model model) {
        List<AnnouncementCardDto> announcements = announcementService.findByUser(principal.getName());
        model.addAttribute("announcements", announcements);
        return "userAnnouncement";
    }
}
