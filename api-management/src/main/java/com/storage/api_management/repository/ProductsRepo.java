package com.storage.api_management.repository;

import com.storage.api_management.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductsRepo extends MongoRepository<Products, String> {

    List<Products> findByIdCategory(String idCategory);
}
