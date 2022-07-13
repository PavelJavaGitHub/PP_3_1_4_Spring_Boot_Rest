package ru.pavel_java_dev.spring_boot_rest.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pavel_java_dev.spring_boot_rest.models.Role;
import ru.pavel_java_dev.spring_boot_rest.models.User;
import ru.pavel_java_dev.spring_boot_rest.services.UserDetailsServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String showAdminPage(@AuthenticationPrincipal User user, @ModelAttribute("createdUser") User createdUser, Model model) {
        model.addAttribute("user", userDetailsServiceImpl.getUserById(user.getId()));
        model.addAttribute("createdUser", createdUser);
        model.addAttribute("users", userDetailsServiceImpl.listAllUsers());
        model.addAttribute("allRoles", Role.values());
        return "bootstrap/admin";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String showEditUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDetailsServiceImpl.getUserById(id));
        model.addAttribute("allRoles", Role.values());
        return "bootstrap/admin";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        userDetailsServiceImpl.deleteUserById(user.getId());
        return "redirect:/admin";
    }
}
