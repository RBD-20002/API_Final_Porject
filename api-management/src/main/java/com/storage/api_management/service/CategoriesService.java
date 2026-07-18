package com.storage.api_management.service;

import com.storage.api_management.dto.CategoryCreateDTO;
import com.storage.api_management.entity.Categories;
import com.storage.api_management.repository.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepo categoriesRepo;

    public List<Categories> getAllCategories(){
        return categoriesRepo.findAll();
    }

    public Categories createCategory(CategoryCreateDTO dto){
        Categories category = new Categories();
        category.setCatName(dto.getCatName());

        return categoriesRepo.save(category);
    }

    public Categories getCategoryById(String id){
        return categoriesRepo.findByIdCategory(id).orElseThrow(() -> new RuntimeException("Category not found with ID: "+id));
    }
}