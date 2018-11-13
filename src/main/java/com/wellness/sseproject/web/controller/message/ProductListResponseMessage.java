package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductListResponseMessage extends ApiResponseMessage {

    List<ProductDTO> productList;

    public ProductListResponseMessage(List<ProductDTO> productDTOList){
        this.setHttpStatusCodeOK();
        this.productList = productDTOList;
    }


}
