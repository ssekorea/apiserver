package com.wellness.sseproject.web.controller.dto;

import com.wellness.sseproject.domain.ProductImage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductImageDTO {

    private int productImageId;
    private int productId;
    private String productImageUrl;

    public ProductImageDTO(){

    }

    public ProductImageDTO(ProductImage productImage){
        this.productImageId = productImage.getProductImageId();
        this.productId = productImage.getProduct().getProductId();
        this.productImageUrl = productImage.getImageUrl();
    }

    public ProductImageDTO(int productImageId, int productId, String productImageUrl) {
        this.productImageId = productImageId;
        this.productId = productId;
        this.productImageUrl = productImageUrl;
    }
}
