package com.demo.service;

import com.demo.model.OrderDataDo;

import java.util.List;

public interface OrderDataService {
    void updateOrderStatus(String id, String status);

    void insert(OrderDataDo orderData);
}
