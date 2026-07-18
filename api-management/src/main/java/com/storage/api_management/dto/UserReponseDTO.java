package com.storage.api_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserReponseDTO {
    private String idUser;
    private String userName;
    private String userEmail;
}
