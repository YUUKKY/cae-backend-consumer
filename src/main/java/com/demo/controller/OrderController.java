package com.demo.controller;

import com.demo.service.DmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@RestController
@RequestMapping("/v1")
public class OrderController {

    @Value("${order_speed:1}")
    private int orderSpeed; // 读取环境变量，默认1

    @Autowired
    private DmsService dmsService;

    @PostMapping("/orders")
    public ResponseEntity<Map<String, Object>> generateOrder() {
        // 生成订单内容
        Map<String, Object> order = new HashMap<>();
        order.put("id", UUID.randomUUID().toString());
        order.put("timestamp", System.currentTimeMillis());
        order.put("quantity", orderSpeed);
        order.put("status", "NEW");
        order.put("price", 100 + new Random().nextInt(20));

        // 推送到DMS
        boolean success = dmsService.pushOrder(order);

        if (success) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to push order to DMS"));
        }
    }
}