package com.demo.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDataMapper {
    void updateOrderStatus(String id, Long timestamp, String status);
}
