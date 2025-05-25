package com.demo.service;

import com.demo.model.OrderDataDo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderConsumer {

    public static final String TOPIC = "orders";
    private final OrderDataService orderDataService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaOrderConsumer(OrderDataService orderDataService) {
        this.orderDataService = orderDataService;
    }

    @KafkaListener(topics = {TOPIC})
    public void listen(String message) {
        try {
            OrderDataDo order = objectMapper.readValue(message, OrderDataDo.class);
            // 消费后状态改为 FINISH
            orderDataService.updateOrderStatus(order.getId(), order.getTimestamp(), "FINISH");
            System.out.println("Order saved (status=FINISH): " + order.getId());
        } catch (Exception e) {
            System.err.println("Failed to process order message: " + message);
            e.printStackTrace();
        }
    }
}
