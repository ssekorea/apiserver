package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.domain.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderListResponseMessage extends ApiResponseMessage {

    private List<Order> orderList;

    public OrderListResponseMessage(List<Order> orderList){
        this.orderList = orderList;
        this.setHttpStatusCodeOK();
    }
}
