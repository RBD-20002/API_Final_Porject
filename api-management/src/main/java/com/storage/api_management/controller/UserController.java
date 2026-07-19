package com.storage.api_management.controller;

import com.storage.api_management.dto.UserCreateDTO;
import com.storage.api_management.dto.UserLoginDTO;
import com.storage.api_management.entity.User;
import com.storage.api_management.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public User createUser(@RequestBody UserCreateDTO dto){
        return usersService.registerUser(dto);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLoginDTO dto){
        return usersService.login(dto);
    }
}