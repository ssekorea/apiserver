package com.wellness.sseproject.web.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductRegisterDTO {

    String name;
    String explanation;
    int price;
    int stock;
    List<String> productImageUrls = new ArrayList<>();
}
