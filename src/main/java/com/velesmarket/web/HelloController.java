package com.velesmarket.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HelloController {

    @GetMapping("/")
    public String mainPage(Principal principal, Model model) {
        model.addAttribute("userNotSignedIn", principal == null);
        return "mainPage";
    }

    @GetMapping("/announcements")
    public String getAnnouncement() {
        return "announcement";
    }
}
