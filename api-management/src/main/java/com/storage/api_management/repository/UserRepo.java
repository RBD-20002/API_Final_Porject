package com.storage.api_management.repository;

import com.storage.api_management.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
}
