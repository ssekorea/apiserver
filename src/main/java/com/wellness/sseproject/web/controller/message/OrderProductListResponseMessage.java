package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.OrderProductDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class OrderProductListResponseMessage extends ApiResponseMessage {

    List<OrderProductDTO> orderProductList;

    public OrderProductListResponseMessage(List<OrderProductDTO> orderProductList){
        this.setHttpStatusCodeOK();
        this.orderProductList = orderProductList;
    }
}
