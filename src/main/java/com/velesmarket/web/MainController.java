package com.velesmarket.web;

import com.velesmarket.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AnnouncementService announcementService;

    @GetMapping("/")
    public String mainPage(Principal principal, Model model) {
        model.addAttribute("userNotSignedIn", principal == null);
        model.addAttribute("announcements", announcementService.getAllCards());
        return "mainPage";
    }

    @GetMapping("/announcements")
    public String getAnnouncement() {
        return "announcement";
    }
}
