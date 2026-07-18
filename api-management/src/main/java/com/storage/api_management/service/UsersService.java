package com.storage.api_management.service;

import com.storage.api_management.dto.UserCreateDTO;
import com.storage.api_management.dto.UserLoginDTO;
import com.storage.api_management.entity.User;
import com.storage.api_management.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UserRepo userRepo;

    public User registerUser(UserCreateDTO dto){
        if(userRepo.findByEmail(dto.getUserEmail()).isPresent()){
            throw new RuntimeException("The email address is already registered");
        }

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        user.setUserPassword(dto.getUserPassword());

        return userRepo.save(user);
    }

    public User login(UserLoginDTO dto){
        User user = userRepo.findByEmail(dto.getUserEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getUserPassword().equals(dto.getUserPassword())){
            throw new RuntimeException("Incorrect password");
        }
        return user;
    }
}
