package com.wellness.sseproject.web.controller.dto;

import com.wellness.sseproject.domain.Order;
import com.wellness.sseproject.domain.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderProductDTO {

    private Order order;
    private Product product;

    public OrderProductDTO(Order order, Product product){
        this.order = order;
        this.product = product;
    }


}
