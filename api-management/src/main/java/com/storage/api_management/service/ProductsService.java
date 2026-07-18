package com.storage.api_management.service;

import com.storage.api_management.dto.ProductCreateDTO;
import com.storage.api_management.entity.Products;
import com.storage.api_management.repository.CategoriesRepo;
import com.storage.api_management.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private CategoriesRepo categoriesRepo;

    public List<Products> getAllProduct(){
        return productsRepo.findAll();
    }

    public List<Products> getProductByCategories(String idCategory){
        return productsRepo.findByIdCategory(idCategory);
    }

    public Products createProduct(ProductCreateDTO dto){
        categoriesRepo.findByIdCategory(dto.getIdCategory());

        Products product = new Products();
        product.setProName(dto.getProName());
        product.setProAmount(dto.getProAmount());
        product.setProPrice(dto.getProPrice());
        product.setProMarketName(dto.getProMarketName());
        product.setIdCategory(dto.getIdCategory());
        product.setDynamicAttributes(dto.getDynamicAttributes());

        return productsRepo.save(product);
    }

    public String deleteProduct(String id){
        if(productsRepo.findById(id).isPresent()){
            productsRepo.deleteById(id);
            return "Product removed";
        }
        return "Product not found";
    }
}