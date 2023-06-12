package com.velesmarket.web;

import com.velesmarket.domain.CategoryDto;
import com.velesmarket.service.AnnouncementService;
import com.velesmarket.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AnnouncementService announcementService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String mainPage(Principal principal, Model model) {
        List<CategoryDto> categories = categoryService.getAll();
        model.addAttribute("userNotSignedIn", principal == null);
        model.addAttribute("announcements", announcementService.getFirstPage());
        model.addAttribute("categories", categories);
        return "mainPage";
    }
}
