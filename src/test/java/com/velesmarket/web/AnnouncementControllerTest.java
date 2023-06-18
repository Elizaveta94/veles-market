package com.velesmarket.web;

import com.velesmarket.domain.AnnouncementCreateRequest;
import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.domain.CategoryDto;
import com.velesmarket.domain.UserDto;
import com.velesmarket.service.AnnouncementService;
import com.velesmarket.service.CategoryService;
import com.velesmarket.service.FeatureViewService;
import com.velesmarket.service.feature.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnnouncementControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private AnnouncementService announcementService;

    @Mock
    private FeatureViewService featureViewService;

    @InjectMocks
    private AnnouncementController announcementController;

    @Test
    void testGetAnnouncementShouldGetAnnouncementByIdAndAddAttributesToModelAndReturnAnnouncementView() {
        // Arrange
        Long announcementId = 1L;
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        AnnouncementDto announcementDto = new AnnouncementDto();
        CategoryDto categoryDto = new CategoryDto();
        List<Feature> features = List.of(
                Feature.builder().property("prop1").name("PROP ONE").build(),
                Feature.builder().property("prop2").name("PROP TWO").build()
        );

        announcementDto.setUser(new UserDto());
        announcementDto.setCategory(categoryDto);
        announcementDto.setFeatureMap(Map.of("prop1", "val1", "prop2", "val2", "prop3", "null"));

        when(announcementService.get(announcementId)).thenReturn(announcementDto);
        when(featureViewService.getFeaturesView(categoryDto.getFeatureCategory())).thenReturn(features);
        when(principal.getName()).thenReturn("testuser");

        // Act
        String viewName = announcementController.getAnnouncement(announcementId, model, principal);

        // Assert
        verify(announcementService).get(announcementId);
        verify(featureViewService).getFeaturesView(categoryDto.getFeatureCategory());
        verify(model).addAttribute("announcement", announcementDto);
        verify(model).addAttribute("isCreator", false);
        verify(model).addAttribute("featureMap", Map.of("PROP ONE", "val1", "PROP TWO", "val2"));
        assertEquals("announcement", viewName);
    }

    @Test
    void testDeleteAnnouncementShouldDeleteAnnouncementByIdAndRedirectToRoot() {
        // Arrange
        Long announcementId = 1L;
        Principal principal = mock(Principal.class);
        String userName = "testuser";
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setUser(UserDto.builder().login(userName).build());
        when(announcementService.get(announcementId)).thenReturn(announcementDto);
        when(principal.getName()).thenReturn(userName);

        // Act
        String viewName = announcementController.deleteAnnouncement(announcementId, principal);

        // Assert
        verify(announcementService).get(announcementId);
        verify(announcementService).delete(announcementId);
        assertEquals("redirect:/", viewName);
    }

    @Test
    void testGetCreateFormShouldAddCategoriesAndAnnouncementToModelAndReturnCreateAdFormView() {
        // Arrange
        Model model = mock(Model.class);
        List<CategoryDto> categories = new ArrayList<>();
        when(categoryService.getAll()).thenReturn(categories);

        // Act
        String viewName = announcementController.getCreateForm(model);

        // Assert
        verify(categoryService).getAll();
        verify(model).addAttribute("categories", categories);
        verify(model).addAttribute(eq("announcement"), any(AnnouncementDto.class));
        assertEquals("createAnAd", viewName);
    }

    @Test
    void testCreateAnnouncementShouldCreateAnnouncementAndRedirectToAnnouncementView() {
        // Arrange
        AnnouncementCreateRequest announcement = new AnnouncementCreateRequest();
        announcement.setPhotosAnnouncement(new ArrayList<>());
        Principal principal = mock(Principal.class);
        AnnouncementDto createdAnnouncement = new AnnouncementDto();
        createdAnnouncement.setId(1L);
        when(announcementService.create(announcement, principal.getName())).thenReturn(createdAnnouncement);

        // Act
        String viewName = announcementController.createAnnouncement(announcement, principal);

        // Assert
        verify(announcementService).create(announcement, principal.getName());
        assertEquals("redirect:/announcement/1", viewName);
    }
}