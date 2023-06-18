package com.velesmarket.web;

import com.velesmarket.domain.AnnouncementCardDto;
import com.velesmarket.domain.UserDto;
import com.velesmarket.service.AnnouncementService;
import com.velesmarket.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AnnouncementService announcementService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetRegistrationFormShouldAddUserDtoToModelAndReturnRegistrationView() {
        // Arrange
        Model model = mock(Model.class);

        // Act
        String viewName = userController.getRegistrationForm(model);

        // Assert
        verify(model).addAttribute(eq("user"), eq(new UserDto()));
        assertEquals("registration", viewName);
    }

    @Test
    void testRegistrationSubmitShouldRegisterUserAndRedirectToProfileView() {
        // Arrange
        UserDto userDto = new UserDto();
        Model model = mock(Model.class);
        when(userService.registrate(userDto)).thenReturn(userDto);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getLogin(), userDto.getPassword());
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication);

        // Act
        String viewName = userController.registrationSubmit(userDto, model);

        // Assert
        verify(userService).registrate(userDto);
        verify(authenticationManager).authenticate(authenticationToken);
        verify(model, never()).addAttribute(anyString(), any());
        assertEquals("redirect:/profile", viewName);
    }

    @Test
    void testProfileShouldGetUserFromServiceAndAddUserToModelAndReturnUserProfileView() {
        // Arrange
        Principal principal = mock(Principal.class);
        String username = "testuser";
        UserDto userDto = new UserDto();
        Model model = mock(Model.class);
        when(principal.getName()).thenReturn(username);
        when(userService.getUser(username)).thenReturn(userDto);

        // Act
        String viewName = userController.profile(principal, model);

        // Assert
        verify(userService).getUser(username);
        verify(model).addAttribute("user", userDto);
        assertEquals("userProfile", viewName);
    }

    @Test
    void testEditProfileShouldGetUserFromServiceAndAddUserToModelAndReturnEditProfileView() {
        // Arrange
        Principal principal = mock(Principal.class);
        String username = "testuser";
        UserDto userDto = new UserDto();
        Model model = mock(Model.class);
        when(principal.getName()).thenReturn(username);
        when(userService.getUser(username)).thenReturn(userDto);

        // Act
        String viewName = userController.editProfile(principal, model);

        // Assert
        verify(userService).getUser(username);
        verify(model).addAttribute("user", userDto);
        assertEquals("editProfile", viewName);
    }

    @Test
    void testGetUserAnnouncementShouldGetAnnouncementsByUserAndAddToModelAndReturnUserAnnouncementView() {
        // Arrange
        Principal principal = mock(Principal.class);
        String username = "testuser";
        Model model = mock(Model.class);
        List<AnnouncementCardDto> announcements = new ArrayList<>();
        when(principal.getName()).thenReturn(username);
        when(announcementService.findByUser(username)).thenReturn(announcements);

        // Act
        String viewName = userController.getUserAnnouncement(principal, model);

        // Assert
        verify(announcementService).findByUser(username);
        verify(model).addAttribute("announcements", announcements);
        assertEquals("userAnnouncement", viewName);
    }
}