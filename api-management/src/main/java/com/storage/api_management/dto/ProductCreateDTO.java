package com.storage.api_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductCreateDTO {
    private String proName;
    private Integer proAmount;
    private Double proPrice;
    private String proMarketName;
    private String proStatus;
    private String imageUrl = null;
    private String idCategory;
    private String idUser;
    private Map<String, Object> dynamicAttributes;
}
