package com.wellness.sseproject.domain.repository;


import com.wellness.sseproject.domain.Order;
import com.wellness.sseproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOrderByOrderId(int orderId);

    @Query(value = "SELECT * FROM ORDER_TB ORDER BY is_payment_confirm ASC LIMIT :startCount, :pageCount", nativeQuery = true)
    List<Order> findOrderListByPage(@Param("startCount")int startCount, @Param("pageCount")int count);

    @Query(value = "SELECT * FROM ORDER_TB WHERE user_id = :userId ORDER BY product_id LIMIT :startCount, :pageCount", nativeQuery = true)
    List<Order> findOrderListByUserIdAndPage(@Param("userId")String userId, @Param("startCount")int startCount, @Param("pageCount")int count);
}
