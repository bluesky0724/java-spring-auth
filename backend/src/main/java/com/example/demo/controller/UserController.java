package com.example.demo.controller;

import com.example.demo.data.User;
import com.example.demo.dto.UserDto;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> findAll() { return userService.findAll(); }

    // ToDo: email validation and password exist?
    @GetMapping("auth/{id}")
    public User findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User register(@RequestBody UserDto userDto) {
        try {
            User old = userService.findByEmail(userDto.email);
            return null;
        }
        catch (Exception e) {
            // Todo: encrypt password with some module.
            User user = new User(userDto.userName, userDto.password, userDto.email);

            // Todo: make hash of passowrd and save
            return userService.save(user);
        }

    }

    @PostMapping("/auth/ddd")
    public User login(@RequestBody UserDto userDto) {
        User user = userService.checkPassword(userDto.email, userDto.password);
        return user;
    }
}
