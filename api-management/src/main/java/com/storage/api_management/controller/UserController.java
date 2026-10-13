package com.storage.api_management.controller;

import com.storage.api_management.dto.UserCreateDTO;
import com.storage.api_management.dto.UserLoginDTO;
import com.storage.api_management.dto.UserResponseDTO;
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
    public UserResponseDTO createUser(@RequestBody UserCreateDTO dto){
        return usersService.registerUser(dto);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody UserLoginDTO dto){
        return usersService.login(dto);
    }
}