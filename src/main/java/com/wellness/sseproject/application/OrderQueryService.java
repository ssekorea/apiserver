package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Order;
import com.wellness.sseproject.domain.Product;
import com.wellness.sseproject.domain.ProductImage;
import com.wellness.sseproject.domain.repository.OrderRepository;
import com.wellness.sseproject.domain.repository.ProductRepository;
import com.wellness.sseproject.web.controller.dto.OrderProductDTO;
import com.wellness.sseproject.web.controller.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderQueryService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public OrderProductDTO getOrderProductById(int orderId){

        Order order = orderRepository.findOrderByOrderId(orderId);
        if (order == null){
            return null;
        }

        Product product = productRepository.findByProductId(order.getProductId());
        OrderProductDTO orderProductDTO = new OrderProductDTO(order, product);
        return orderProductDTO;
    }

    public List<Order> getOrderListByPage(int startIndex, int count) {

        int startCount = count * startIndex;

        List<Order> orderList = orderRepository.findOrderListByPage(startCount, count);
        if (orderList.size() == 0){
            return null;
        }
        return orderList;
    }

    public List<OrderProductDTO> getOrderListByUserIdAndPage(String userId, int startIndex, int count){

        int startCount = count * startIndex;

        List<Order> orderList = orderRepository.findOrderListByUserIdAndPage(userId, startCount, count);

        if (orderList.size() == 0){
            return null;
        }

        List<Integer> tempOrderIdList = new ArrayList<>();
        for (Order order: orderList){
            tempOrderIdList.add(order.getProductId());
        }

        List<Product> productList = productRepository.findProductListByProductIdList(tempOrderIdList);
        List<OrderProductDTO> orderProductDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++){
            for (int j = 0; j < productList.size(); j++){
                if (orderList.get(i).getProductId() == productList.get(j).getProductId()){
                    orderProductDTOList.add(new OrderProductDTO(orderList.get(i), productList.get(j)));
                }
            }
        }

        return orderProductDTOList;

    }
}
