package com.velesmarket.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VelesErrorControllerTest {

    @Mock
    private ErrorAttributes errorAttributes;

    @InjectMocks
    private VelesErrorController errorController;

    @Test
    void testHandleErrorShouldSetStatusCodeAttributeInModel() {
        // Arrange
        int expectedStatusCode = 404;
        Map<String, Object> errorAttributesMap = new HashMap<>();
        errorAttributesMap.put("status", expectedStatusCode);
        WebRequest webRequest = mock(WebRequest.class);
        Model model = mock(Model.class);

        when(errorAttributes.getErrorAttributes(same(webRequest), any()))
                .thenReturn(errorAttributesMap);

        // Act
        String viewName = errorController.handleError(webRequest, model);

        // Assert
        verify(model).addAttribute("statusCode", expectedStatusCode);
        assertEquals("error", viewName);
    }
}