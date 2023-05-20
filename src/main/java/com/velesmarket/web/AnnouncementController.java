package com.velesmarket.web;

import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.domain.CategoryDto;
import com.velesmarket.domain.LocationDto;
import com.velesmarket.domain.UserDto;
import com.velesmarket.service.AnnouncementService;
import com.velesmarket.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final CategoryService categoryService;
    private final AnnouncementService announcementService;

    @GetMapping("/create")
    public String getCreateForm(Model model) {
        List<CategoryDto> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("announcement", AnnouncementDto.builder()
                .location(new LocationDto()).category(new CategoryDto()).build());

        return "createAnAd";
    }

    @PostMapping("/create")
    public String createAnnouncement(@ModelAttribute AnnouncementDto announcement, Model model, Principal principal) {
        announcementService.create(announcement, principal.getName());
        return "createAnAd";
    }

}
