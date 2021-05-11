package com.deepamin.springreactawstodo.controller;

import com.deepamin.springreactawstodo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //TODO sign-up in login page
    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }
}
