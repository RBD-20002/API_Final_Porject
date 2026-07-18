package com.storage.api_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductUpdateDTO {
    private String idProduct;
    private String proName;
    private Integer proAmount;
    private Double proPrice;
    private String proMarketName;
    private String idCategory;
    private Map<String, Object> dynamicAttributes;
}