package com.demo.mapper;

import com.demo.model.OrderDataDo;
import com.demo.model.UserDataDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDataMapper {
    void updateOrderStatus(String id, Long timestamp, String status);
}
