package com.wellness.sseproject.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellness.sseproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDTO implements Serializable, UserDetails{

    private String userId;
    private String password;
    private String name;
    private String birthDate;
    private String phoneNumber;
    private String sex;
    private String registerType;
    private String userType;
    private int heartDisease;
    private int cancer;
    private int highBloodPressure;
    private int diabetes;
    private int arthritis;
    private int dementia;

    private String token;

    @JsonIgnore
    Collection<? extends GrantedAuthority> authorities;

    public UserInfoDTO(){

    }


    public UserInfoDTO(User user){
        this.userId = user.getUserId();
        this.password = "";
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
        this.sex = user.getSex();
        this.heartDisease = user.getHeartDisease();
        this.cancer = user.getCancer();
        this.highBloodPressure = user.getHighBloodPressure();
        this.diabetes = user.getDiabetes();
        this.arthritis = user.getArthritis();
        this.dementia = user.getDementia();
        this.userType = user.getUserType();
        this.registerType = user.getRegisterType();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userType));
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return false;
    }
}
