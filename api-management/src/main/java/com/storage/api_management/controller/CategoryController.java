package com.storage.api_management.controller;

import com.storage.api_management.dto.CategoryCreateDTO;
import com.storage.api_management.entity.Category;
import com.storage.api_management.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public List<Category> allCategories(){
        return categoriesService.getAllCategories();
    }

    @PostMapping
    public Category createCategories(@RequestBody CategoryCreateDTO dto){
        return categoriesService.createCategory(dto);
    }

    @GetMapping("/{id}")
    public Category categoryById(@PathVariable String id){
        return categoriesService.getCategoryById(id);
    }
}