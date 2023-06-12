package com.velesmarket.web;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
@Controller
@RequiredArgsConstructor
public class VelesErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String handleError(WebRequest webRequest, Model model) {
        Map<String, Object> errorAttributesMap = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());

        int statusCode = (int) errorAttributesMap.get("status");

        model.addAttribute("statusCode", statusCode);
        return "error";
    }
}

