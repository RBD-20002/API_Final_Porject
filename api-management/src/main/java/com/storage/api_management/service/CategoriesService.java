package com.storage.api_management.service;

import com.storage.api_management.dto.CategoryCreateDTO;
import com.storage.api_management.dto.CategoryUpdateDTO;
import com.storage.api_management.entity.Category;
import com.storage.api_management.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getCategoriesByUser(String idUser){
        return categoryRepo.findByIdUser(idUser);
    }

    public Category createCategory(CategoryCreateDTO dto){
        Category category = new Category();
        category.setCatName(dto.getCatName());
        category.setCatName(dto.getCatName());
        category.setCatIconKey(dto.getCatIconKey());
        category.setIdUser(dto.getIdUser());

        return categoryRepo.save(category);
    }

    public Category updateCategory(CategoryUpdateDTO dto) {
        Category category = getCategoryById(dto.getIdCategory());
        category.setCatName(dto.getCatName());
        category.setCatIconKey(dto.getCatIconkey());
        return categoryRepo.save(category);
    }

    public Category getCategoryById(String id){
        return categoryRepo.findByIdCategory(id).orElseThrow(() -> new RuntimeException("Category not found with ID: "+id));
    }
}