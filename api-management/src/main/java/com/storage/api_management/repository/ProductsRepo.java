package com.storage.api_management.repository;

import com.storage.api_management.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepo extends MongoRepository<Products, String> {
}
