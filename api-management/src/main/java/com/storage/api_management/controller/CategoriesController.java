package com.storage.api_management.controller;

import com.storage.api_management.dto.CategoryCreateDTO;
import com.storage.api_management.entity.Categories;
import com.storage.api_management.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public List<Categories> allCategories(){
        return categoriesService.getAllCategories();
    }

    @PostMapping
    public Categories createCategories(@RequestBody CategoryCreateDTO dto){
        return categoriesService.createCategory(dto);
    }

    @GetMapping("/{id}")
    public Categories categoryById(@PathVariable String id){
        return categoriesService.getCategoryById(id);
    }
}