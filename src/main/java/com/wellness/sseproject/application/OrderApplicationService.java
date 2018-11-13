package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Order;
import com.wellness.sseproject.domain.repository.OrderRepository;
import com.wellness.sseproject.web.controller.dto.OrderModifyDTO;
import com.wellness.sseproject.web.controller.dto.OrderRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderApplicationService {

    @Autowired
    OrderRepository orderRepository;

    public boolean deleteOrder(int orderId){

        if (orderRepository.findOrderByOrderId(orderId) == null){
            return false;
        }
        orderRepository.deleteById(orderId);
        return true;

    }

    public Order modifyOrder(int orderId, OrderModifyDTO orderModifyDTO) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        if (order == null){
            return null;
        }
        order.setOrderPrice(orderModifyDTO.getOrderPrice());
        order.setPaymentInfo(orderModifyDTO.getPaymentInfo());
        order.setOrderNum(orderModifyDTO.getOrderNum());
        order.setPaymentConfirm(orderModifyDTO.isPaymentConfirm());

        orderRepository.save(order);
        return order;
    }

    public Order registerOrder(OrderRegisterDTO orderRegisterDTO) {
        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        orderRegisterDTO.setOrderDate(date.format(today.getTime()));
        Order order = new Order(orderRegisterDTO);
        return orderRepository.save(order);
    }
}
