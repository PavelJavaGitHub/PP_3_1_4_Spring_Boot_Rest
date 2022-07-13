package ru.pavel_java_dev.spring_boot_rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.pavel_java_dev.spring_boot_rest.models.User;
import ru.pavel_java_dev.spring_boot_rest.services.UserDetailsServiceImpl;

@Controller
public class MainController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showLoginPage() {
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String showUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "bootstrap/user";
    }

    @GetMapping("user/{id}")
    public String showUserPageById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDetailsServiceImpl.getUserById(id));
        return "bootstrap/user";
    }

    @RequestMapping("user/getOne")
    @ResponseBody
    public User getUserById(long id) {
        return userDetailsServiceImpl.getUserById(id);
    }

    @GetMapping("test")
    public String test() {
        return "bootstrap/test";
    }
}
