package com.wellness.sseproject.domain;

import com.wellness.sseproject.web.controller.dto.OrderRegisterDTO;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_TB")
@Data
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    int orderId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "product_id")
    int productId;

    @Column(name = "order_num")
    int orderNum;

    @Column(name = "order_price")
    int orderPrice;

    @Column(name = "order_date")
    String orderDate;

    @Column(name = "payment_info")
    int paymentInfo;

    @Column(name = "delivery_state")
    int delivery_state;

    @Column(name = "is_payment_confirm")
    boolean isPaymentConfirm;

    public Order(OrderRegisterDTO orderRegisterDTO){
        this.userId = orderRegisterDTO.getUserId();
        this.productId = orderRegisterDTO.getProductId();
        this.orderNum = orderRegisterDTO.getOrderNum();
        this.orderPrice = orderRegisterDTO.getOrderPrice();
        this.orderDate = orderRegisterDTO.getOrderDate();
        this.paymentInfo = orderRegisterDTO.getPaymentInfo();
        this.isPaymentConfirm = orderRegisterDTO.isPaymentConfirm();
    }

}
