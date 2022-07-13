package ru.pavel_java_dev.spring_boot_rest.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.pavel_java_dev.spring_boot_rest.models.Role;
import ru.pavel_java_dev.spring_boot_rest.models.User;
import ru.pavel_java_dev.spring_boot_rest.services.UserDetailsServiceImpl;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public RestController(UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public List<User> getUsersList() {
        return userDetailsServiceImpl.listAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        return userDetailsServiceImpl.getUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsServiceImpl.saveUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsServiceImpl.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userDetailsServiceImpl.deleteUserById(id);
    }

    @GetMapping("/getActiveUser")
    public User getActiveUser(@AuthenticationPrincipal User user) {
        return userDetailsServiceImpl.getUserById(user.getId());
    }

    @GetMapping("/getAllRoles")
    public Role[] getAllRoles() {
        return Role.values();
    }

}
