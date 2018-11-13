package com.wellness.sseproject.web.controller.dto;

import com.wellness.sseproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String name;
    private String birthDate;
    private String phoneNumber;
    private String sex;
    private String registerDate;
    private String expireDate;
    private int heartDisease;

    private int cancer;

    private int highBloodPressure;

    private int diabetes;

    private int arthritis;

    private int dementia;

    public UserDTO(User user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
        this.sex = user.getSex();
        this.registerDate = user.getRegisterDate();
        this.expireDate = user.getExpireDate();
        this.heartDisease = user.getHeartDisease();
        this.cancer = user.getCancer();
        this.highBloodPressure = user.getHighBloodPressure();
        this.diabetes = user.getDiabetes();
        this.arthritis = user.getArthritis();
        this.dementia = user.getDementia();
    }

}
