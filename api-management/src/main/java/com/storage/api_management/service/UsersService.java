package com.storage.api_management.service;

import com.storage.api_management.dto.UserCreateDTO;
import com.storage.api_management.dto.UserLoginDTO;
import com.storage.api_management.dto.UserResponseDTO;
import com.storage.api_management.entity.User;
import com.storage.api_management.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UserRepo userRepo;

    public UserResponseDTO registerUser(UserCreateDTO dto){
        if(userRepo.findByUserEmail(dto.getUserEmail()).isPresent()){
            throw new RuntimeException("The email address is already registered");
        }

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        user.setUserPassword(dto.getUserPassword());
        User saveUser = userRepo.save(user);

        return new UserResponseDTO(saveUser.getIdUser(), saveUser.getUserName(), saveUser.getUserEmail());
    }

    public UserResponseDTO login(UserLoginDTO dto){
        User user = userRepo.findByUserEmail(dto.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getUserPassword().equals(dto.getUserPassword())){
            throw new RuntimeException("Incorrect password");
        }
        return new UserResponseDTO(user.getIdUser(), user.getUserName(), user.getUserEmail());
    }
}
