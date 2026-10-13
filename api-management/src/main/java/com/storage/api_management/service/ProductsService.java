package com.storage.api_management.service;

import com.storage.api_management.dto.ProductCreateDTO;
import com.storage.api_management.dto.ProductUpdateDTO;
import com.storage.api_management.entity.Product;
import com.storage.api_management.repository.CategoryRepo;
import com.storage.api_management.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Product> getAllProductByUser(String idUser){
        return productRepo.findByIdUser(idUser);
    }

    public List<Product> getProductsByCategoryAndUser(String idCategory, String idUser){
        return productRepo.findByIdCategoryAndIdUser(idCategory, idUser);
    }

    public Product createProduct(ProductCreateDTO dto){
        categoryRepo.findByIdCategory(dto.getIdCategory()).orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setProName(dto.getProName());
        product.setProAmount(dto.getProAmount());
        product.setProPrice(dto.getProPrice());
        product.setProMarketName(dto.getProMarketName());
        product.setProStatus(dto.getProStatus());
        product.setImageUrl(dto.getImageUrl());
        product.setIdCategory(dto.getIdCategory());
        product.setIdUser(dto.getIdUser());
        product.setDynamicAttributes(dto.getDynamicAttributes());

        return productRepo.save(product);
    }

    public Product updateProduct(ProductUpdateDTO dto) {
        Product product = productRepo.findById(dto.getIdProduct())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProName(dto.getProName());
        product.setProAmount(dto.getProAmount());
        product.setProPrice(dto.getProPrice());
        product.setProMarketName(dto.getProMarketName());
        product.setProStatus(dto.getProStatus());
        if (dto.getImageUrl() != null) {
            product.setImageUrl(dto.getImageUrl());
        }
        product.setIdCategory(dto.getIdCategory());
        product.setDynamicAttributes(dto.getDynamicAttributes());
        return productRepo.save(product);
    }

    public String deleteProduct(String id){
        if(productRepo.findById(id).isPresent()){
            productRepo.deleteById(id);
            return "Product removed";
        }
        return "Product not found";
    }
}