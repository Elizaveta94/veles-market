package com.velesmarket.web;

import com.velesmarket.domain.*;
import com.velesmarket.persist.repository.PhotoAnnouncementRepository;
import com.velesmarket.service.AnnouncementService;
import com.velesmarket.service.CategoryService;
import com.velesmarket.service.FeatureViewService;
import com.velesmarket.service.feature.Feature;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final CategoryService categoryService;
    private final AnnouncementService announcementService;
    private final FeatureViewService featureViewService;

    @GetMapping("/{id}")
    public String getAnnouncement(@PathVariable("id") Long id, Model model, Principal principal) {
        AnnouncementDto announcementDto = announcementService.get(id);
        Map<String, String> featureMap = null;
        List<Feature> features = featureViewService.getFeaturesView(announcementDto.getCategory().getFeatureCategory());
        if (features != null && !features.isEmpty()) {
            featureMap = announcementDto.getFeatureMap().entrySet().stream().collect(Collectors.toMap(
                    featureEntry -> features.stream()
                            .filter(f -> f.getProperty().equals(featureEntry.getKey()))
                            .findFirst()
                            .get()
                            .getName()
                    , Map.Entry::getValue
            ));
        }
        model.addAttribute("announcement", announcementDto);
        model.addAttribute("isCreator", principal.getName().equals(announcementDto.getUser().getLogin()));
        model.addAttribute("featureMap", featureMap);
        return "announcement";
    }

    @PostMapping("/{id}")
    public String deleteAnnouncement(@PathVariable("id") Long id, Principal principal) {
        AnnouncementDto announcementDto = announcementService.get(id);
        if (!announcementDto.getUser().getLogin().equals(principal.getName())) {
            throw new AccessDeniedException(principal.getName() + " user are not allowed to delete announcement by id " + id);
        }
        announcementService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model) {
        List<CategoryDto> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("announcement", AnnouncementDto.builder()
                .location(new LocationDto()).category(new CategoryDto()).build());

        return "createAnAd";
    }

    @PostMapping("/create")
    public String createAnnouncement(@ModelAttribute AnnouncementCreateRequest announcement, Principal principal) {
        AnnouncementDto createdAnnouncement = announcementService.create(announcement, principal.getName());
        return "redirect:/announcement/" + createdAnnouncement.getId();
    }

}
