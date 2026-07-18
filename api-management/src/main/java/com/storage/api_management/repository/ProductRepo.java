package com.storage.api_management.repository;

import com.storage.api_management.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String> {

    List<Product> findByIdCategory(String idCategory);
}
