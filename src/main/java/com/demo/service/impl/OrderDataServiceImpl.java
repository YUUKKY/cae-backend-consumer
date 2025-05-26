package com.demo.service.impl;

import com.demo.mapper.OrderDataMapper;
import com.demo.service.OrderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDataServiceImpl implements OrderDataService {
    @Autowired
    private OrderDataMapper dataMapper;

    @Override
    public void updateOrderStatus(String id, String status) {
        dataMapper.updateOrderStatus(id, status);
    }

}
