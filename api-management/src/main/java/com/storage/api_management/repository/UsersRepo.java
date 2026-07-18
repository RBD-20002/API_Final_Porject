package com.storage.api_management.repository;

import com.storage.api_management.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepo extends MongoRepository<Users, String> {

    Optional<Users> findByEmail(String email);
}
