package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.domain.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseMessage extends ApiResponseMessage {

    private Order order;

    public OrderResponseMessage(Order order){
        this.order = order;
        this.setHttpStatusCodeOK();
    }
}
