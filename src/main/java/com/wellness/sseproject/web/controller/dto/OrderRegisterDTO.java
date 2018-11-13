package com.wellness.sseproject.web.controller.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderRegisterDTO {

    private String userId;
    private int productId;
    private int orderNum;
    private int orderPrice;
    private String orderDate;
    private int paymentInfo;

    @JsonIgnore
    private boolean isPaymentConfirm = false;
}
