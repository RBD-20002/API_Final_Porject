package com.storage.api_management.repository;

import com.storage.api_management.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepo extends MongoRepository<Category, String> {

    Optional<Category> findByIdCategory(String idCategory);
}
