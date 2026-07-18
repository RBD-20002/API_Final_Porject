package com.storage.api_management.service;

import com.storage.api_management.dto.CategoryCreateDTO;
import com.storage.api_management.entity.Category;
import com.storage.api_management.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategories(){
        return categoryRepo.findAll();
    }

    public Category createCategory(CategoryCreateDTO dto){
        Category category = new Category();
        category.setCatName(dto.getCatName());

        return categoryRepo.save(category);
    }

    public Category getCategoryById(String id){
        return categoryRepo.findByIdCategory(id).orElseThrow(() -> new RuntimeException("Category not found with ID: "+id));
    }
}