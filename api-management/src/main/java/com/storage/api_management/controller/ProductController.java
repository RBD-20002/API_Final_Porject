package com.storage.api_management.controller;

import com.storage.api_management.dto.ProductCreateDTO;
import com.storage.api_management.dto.ProductUpdateDTO;
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

    @GetMapping("/user/{idUser}")
    public List<Product> allProductByUser(@PathVariable String idUser) {
        return productsService.getAllProductByUser(idUser);
    }

    @GetMapping("/category/{idCategory}/user/{idUser")
    public List<Product> allProductByCategoriesAndUser(@PathVariable String idCategory, @PathVariable String idUser){
        return productsService.getProductsByCategoryAndUser(idCategory, idUser);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductCreateDTO dto){
        return productsService.createProduct(dto);
    }

    @PutMapping
    public Product updateProduct(@RequestBody ProductUpdateDTO dto) {
        return productsService.updateProduct(dto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id){
        return productsService.deleteProduct(id);
    }

}
