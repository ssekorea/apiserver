package com.wellness.sseproject.web.controller.dto;


import com.wellness.sseproject.domain.Product;
import com.wellness.sseproject.domain.ProductImage;
import lombok.Data;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {

    private int productId;
    private String name;
    private String explanation;
    private int price;
    private int stock;
    private List<String> productImageUrls = new ArrayList<>();


    public ProductDTO(Product product){
        this.productId = product.getProductId();
        this.name = product.getName();
        this.explanation = product.getExplanation();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }

    public ProductDTO(int productId, ProductRegisterDTO productRegisterDTO) {
        this.productId = productId;
        this.name = productRegisterDTO.getName();
        this.explanation = productRegisterDTO.getExplanation();
        this.price = productRegisterDTO.getPrice();
        this.stock = productRegisterDTO.getStock();
        this.productImageUrls = productRegisterDTO.getProductImageUrls();
    }

    public void setProductImageUrlsByProductImages(List<ProductImage> productImages){
        productImageUrls = new ArrayList<>();
        for (ProductImage productImage : productImages){
            productImageUrls.add(productImage.getImageUrl());
        }
    }
}
