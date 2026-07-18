package com.storage.api_management.controller;

import com.storage.api_management.dto.ProductCreateDTO;
import com.storage.api_management.entity.Products;
import com.storage.api_management.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/all")
    public List<Products> allProduct(){
        return productsService.getAllProduct();
    }

    @GetMapping("/category/{id}")
    public List<Products> allProductByCategories(@PathVariable String id){
        return productsService.getProductByCategories(id);
    }

    @PostMapping
    public Products createProduct(@RequestBody ProductCreateDTO dto){
        return productsService.createProduct(dto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id){
        return productsService.deleteProduct(id);
    }

}
