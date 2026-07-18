package com.storage.api_management.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "products")
public class Products {

    @Id
    private String idProduct;
    private String proName;
    private Integer proAmount;
    private Double proPrice;
    private String proMarketName;
    private String idCategory;
    private Map<String,Object> dynamicAttributes;
}