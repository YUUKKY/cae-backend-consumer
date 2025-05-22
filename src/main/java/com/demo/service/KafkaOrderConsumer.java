package com.demo.service;

import com.demo.model.OrderDataDo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.mapper.OrderDataMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderConsumer {

    private final OrderDataMapper orderRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaOrderConsumer(OrderDataMapper orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "${order.kafka.topic:orders}", groupId = "order-consumer-group")
    public void listen(String message) {
        try {
            OrderDataDo order = objectMapper.readValue(message, OrderDataDo.class);
            order.setStatus("FINISH"); // 消费后状态改为 FINISH
            orderRepository.updateOrderStatus(order.getId(), order.getTimestamp(), "FINISH");
            System.out.println("Order saved (status=FINISH): " + order.getId());
        } catch (Exception e) {
            System.err.println("Failed to process order message: " + message);
            e.printStackTrace();
        }
    }
}