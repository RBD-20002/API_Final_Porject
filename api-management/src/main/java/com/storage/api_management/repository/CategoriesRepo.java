package com.storage.api_management.repository;

import com.storage.api_management.entity.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoriesRepo extends MongoRepository<Categories, String> {

    Optional<Categories> findByIdCategory(String idCategory);
}
