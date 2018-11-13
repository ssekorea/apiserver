package com.wellness.sseproject.web.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModifyDTO {

    String userId;
    String name;
    String birthDate;
    String phoneNumber;
    String registerDate;
    String expireDate;
    int heartDisease;
    int cancer;
    int highBloodPressure;
    int diabetes;
    int arthritis;
    int dementia;

}
