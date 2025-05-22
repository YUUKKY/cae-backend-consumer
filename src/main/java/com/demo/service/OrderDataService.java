package com.demo.service;


import com.demo.model.OrderDataDo;
import com.demo.model.UserDataDo;

import java.util.List;

public interface OrderDataService {
    void updateOrderStatus(String id, Long timestamp, String status);
}
