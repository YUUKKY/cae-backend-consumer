package com.demo.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDataMapper {
    void updateOrderStatus(String id, String status);

    void insert(String id, String status, Integer quantity, Long timestamp, Integer price, String type);
}
