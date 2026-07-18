package com.storage.api_management.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categories")
public class Categories {

    @Id
    private String idCategory;
    private String catName;
}
