package ru.pavel_java_dev.spring_boot_rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String showLoginPage() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "bootstrap/admin";
    }

    @GetMapping("/user")
    public String showUserPage() {
        return "bootstrap/user";
    }
}
