package com.storage.api_management.service;

import com.storage.api_management.dto.UserCreateDTO;
import com.storage.api_management.dto.UserLoginDTO;
import com.storage.api_management.entity.Users;
import com.storage.api_management.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepo usersRepo;

    public Users registerUser(UserCreateDTO dto){
        if(usersRepo.findByEmail(dto.getUserEmail()).isPresent()){
            throw new RuntimeException("The email address is already registered");
        }

        Users user = new Users();
        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        user.setUserPassword(dto.getUserPassword());

        return usersRepo.save(user);
    }

    public Users login(UserLoginDTO dto){
        Users user = usersRepo.findByEmail(dto.getUserEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getUserPassword().equals(dto.getUserPassword())){
            throw new RuntimeException("Incorrect password");
        }
        return user;
    }
}
