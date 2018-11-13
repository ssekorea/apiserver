package com.wellness.sseproject.web.controller.dto;


import lombok.Data;

@Data
public class LoginDTO{

    private String userId;
    private String password;

    public LoginDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

}
