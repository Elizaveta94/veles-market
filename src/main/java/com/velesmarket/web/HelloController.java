package com.velesmarket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/")
    public String mainPage() {
        return "mainPage";
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
