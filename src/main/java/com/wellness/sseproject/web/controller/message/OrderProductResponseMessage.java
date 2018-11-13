package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.OrderProductDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrderProductResponseMessage extends ApiResponseMessage{

    OrderProductDTO orderProduct;

    public OrderProductResponseMessage(OrderProductDTO orderProduct){
        this.orderProduct = orderProduct;
        this.setHttpStatusCodeOK();
    }

}
