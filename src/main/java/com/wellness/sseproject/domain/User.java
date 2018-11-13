package com.wellness.sseproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellness.sseproject.web.controller.dto.UserInfoDTO;
import com.wellness.sseproject.web.controller.dto.UserModifyDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Data
@Table(name = "USER_TB")
public class User{

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "sex")
    private String sex;
    @Column(name = "register_date")
    private String registerDate;
    @Column(name = "expire_date")
    private String expireDate;

    @JsonIgnore
    @Column(name = "user_type")
    private String userType;
    @Column(name = "register_type")
    private String registerType;
    @Column(name = "heart_disease")
    int heartDisease;
    @Column(name = "cancer")
    int cancer;
    @Column(name = "high_blood_pressure")
    int highBloodPressure;
    @Column(name = "diabetes")
    int diabetes;
    @Column(name = "arthritis")
    int arthritis;
    @Column(name = "dementia")
    int dementia;

    //TODO masking 추가

    public User(){

    }

    public User(String userId, String password, String name, String birthDate, String phoneNumber, String sex, int heartDisease, int cancer, int highBloodPressure, int diabetes, int arthritis, int dementia) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.heartDisease = heartDisease;
        this.cancer = cancer;
        this.highBloodPressure = highBloodPressure;
        this.diabetes = diabetes;
        this.arthritis = arthritis;
        this.dementia = dementia;
    }

    public User(UserInfoDTO userInfoDTO){
        this.userId = userInfoDTO.getUserId();
        this.name = userInfoDTO.getName();
        this.password = userInfoDTO.getPassword();
        this.birthDate = userInfoDTO.getBirthDate();
        this.phoneNumber = userInfoDTO.getPhoneNumber();
        this.sex = userInfoDTO.getSex();
        this.registerDate = null;
        this.expireDate = null;
        this.registerType = userInfoDTO.getRegisterType();
        this.userType = userInfoDTO.getUserType();
        this.heartDisease = userInfoDTO.getHeartDisease();
        this.cancer = userInfoDTO.getCancer();
        this.highBloodPressure = userInfoDTO.getHighBloodPressure();
        this.diabetes = userInfoDTO.getDiabetes();
        this.arthritis = userInfoDTO.getArthritis();
        this.dementia = userInfoDTO.getDementia();
    }

    public GrantedAuthority getGrantedAuthority(){
        return new SimpleGrantedAuthority(userType);
    }


    public void modifyUser(UserModifyDTO userModifyDTO){
        this.birthDate = userModifyDTO.getBirthDate();
        this.phoneNumber = userModifyDTO.getPhoneNumber();
        this.name = userModifyDTO.getName();
        this.dementia = userModifyDTO.getDementia();
        this.heartDisease = userModifyDTO.getHeartDisease();
        this.highBloodPressure = userModifyDTO.getHighBloodPressure();
        this.diabetes= userModifyDTO.getDiabetes();
        this.arthritis = userModifyDTO.getArthritis();
        this.expireDate = userModifyDTO.getExpireDate();
        this.registerDate = userModifyDTO.getRegisterDate();
    }
}
