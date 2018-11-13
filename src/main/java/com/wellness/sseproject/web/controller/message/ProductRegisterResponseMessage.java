package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.ProductRegisterDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductRegisterResponseMessage extends ApiResponseMessage {


    ProductRegisterDTO productRegisterDTO;

    public ProductRegisterResponseMessage(ProductRegisterDTO productRegisterDTO){
        this.setHttpStatusCodeOK();
        this.productRegisterDTO = productRegisterDTO;
    }

}
