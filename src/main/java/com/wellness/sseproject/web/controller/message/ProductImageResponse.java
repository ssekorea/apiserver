package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.ProductImageDTO;
import com.wellness.sseproject.web.controller.dto.ProductRegisterDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductImageResponse extends ApiResponseMessage{

    List<ProductImageDTO> productImages;

    public ProductImageResponse(List<ProductImageDTO> productImageDTOList){
       this.productImages = productImageDTOList;
    }
}
