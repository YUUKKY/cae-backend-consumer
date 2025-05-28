package com.demo.service;

import com.demo.model.OrderDataDo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class KafkaOrderConsumer {

    public static final String TOPIC = "orders";
    private final OrderDataService orderDataService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaOrderConsumer(OrderDataService orderDataService) {
        this.orderDataService = orderDataService;
    }

    @KafkaListener(topics = {TOPIC})
    public void listene(ConsumerRecord<String, String> record) {
        System.out.printf("Received message: topic=%s, partition=%d, offset=%d, key=%s, value=%s%n",
                record.topic(), record.partition(), record.offset(), record.key(), record.value());
        try {
            OrderDataDo order = objectMapper.readValue(record.value(), OrderDataDo.class);
            // 消费后状态改为 FINISH
            if (Objects.equals(order.getType(), "agent")) {
                order.setStatus("FINISH");
                orderDataService.insert(order);
            } else {
                orderDataService.updateOrderStatus(order.getId(), "FINISH");
            }
            System.out.println("Order saved (status=FINISH): " + order.getId());
        } catch (Exception e) {
            System.err.println("Failed to process order message: " + e);
            e.printStackTrace();
        }
    }
}
