package com.wellness.sseproject.web.controller.message;


import com.wellness.sseproject.web.controller.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseMessage extends ApiResponseMessage {

    ProductDTO product;

    public ProductResponseMessage(ProductDTO productDTO){
        this.setHttpStatusCodeOK();
        this.product = productDTO;
    }

}
