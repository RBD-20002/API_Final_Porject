package com.storage.api_management.controller;

import com.storage.api_management.dto.ProductCreateDTO;
import com.storage.api_management.entity.Product;
import com.storage.api_management.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/all")
    public List<Product> allProduct(){
        return productsService.getAllProduct();
    }

    @GetMapping("/category/{id}")
    public List<Product> allProductByCategories(@PathVariable String id){
        return productsService.getProductByCategories(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductCreateDTO dto){
        return productsService.createProduct(dto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id){
        return productsService.deleteProduct(id);
    }

}
