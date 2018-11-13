package com.wellness.sseproject.web.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrderModifyDTO {

    int orderId;
    int orderNum;
    int orderPrice;
    int paymentInfo;
    boolean isPaymentConfirm;
}
