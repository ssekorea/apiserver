package com.wellness.sseproject.domain;

import com.wellness.sseproject.web.controller.dto.ProductDTO;
import com.wellness.sseproject.web.controller.dto.ProductRegisterDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCT_TB")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "name")
    private String name;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "price")
    private int price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "masking")
    private boolean masking;

    public Product(ProductRegisterDTO productRegisterDTO){
        this.name = productRegisterDTO.getName();
        this.explanation = productRegisterDTO.getExplanation();
        this.price = productRegisterDTO.getPrice();
        this.stock = productRegisterDTO.getStock();
    }

    public Product(ProductDTO productDTO){
        this.name = productDTO.getName();
        this.explanation = productDTO.getExplanation();
        this.price = productDTO.getPrice();
        this.stock = productDTO.getStock();
    }

    private Product(){

    }


}
