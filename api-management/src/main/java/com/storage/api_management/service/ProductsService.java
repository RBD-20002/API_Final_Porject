package com.storage.api_management.service;

import com.storage.api_management.dto.ProductCreateDTO;
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

    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }

    public List<Product> getProductByCategories(String idCategory){
        return productRepo.findByIdCategory(idCategory);
    }

    public Product createProduct(ProductCreateDTO dto){
        categoryRepo.findByIdCategory(dto.getIdCategory());

        Product product = new Product();
        product.setProName(dto.getProName());
        product.setProAmount(dto.getProAmount());
        product.setProPrice(dto.getProPrice());
        product.setProMarketName(dto.getProMarketName());
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