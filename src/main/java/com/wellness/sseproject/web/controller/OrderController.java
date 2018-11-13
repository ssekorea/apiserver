package com.wellness.sseproject.web.controller;

import com.wellness.sseproject.application.CourseApplicationService;
import com.wellness.sseproject.application.OrderApplicationService;
import com.wellness.sseproject.application.OrderQueryService;
import com.wellness.sseproject.domain.Order;
import com.wellness.sseproject.web.controller.dto.CourseLectureDTO;
import com.wellness.sseproject.web.controller.dto.OrderModifyDTO;
import com.wellness.sseproject.web.controller.dto.OrderProductDTO;
import com.wellness.sseproject.web.controller.message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    OrderApplicationService orderApplicationService;

    @Autowired
    OrderQueryService orderQueryService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getOrderList(@RequestParam(defaultValue = "0") int startIndex, @RequestParam(defaultValue = "10") int count) {

        List<Order> orderList = orderQueryService.getOrderListByPage(startIndex, count);
        if (orderList == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.
                    createErrorResponseMessageFactory("There is No page", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new OrderListResponseMessage(orderList);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }


    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrderByOrderId(@PathVariable int orderId) {
        OrderProductDTO orderProductDTO = orderQueryService.getOrderProductById(orderId);
        if (orderProductDTO == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid orderId", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new OrderProductResponseMessage(orderProductDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponseMessage> deleteOrder(@PathVariable int orderId) {

        if (!orderApplicationService.deleteOrder(orderId)) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid orderId", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity modifyOrder(@PathVariable int orderId, @RequestBody OrderModifyDTO orderModifyDTO) {
        orderModifyDTO.setOrderId(orderId);
        Order order = orderApplicationService.modifyOrder(orderId, orderModifyDTO);

        if (order == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid orderId", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new OrderResponseMessage(order);
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }
}
