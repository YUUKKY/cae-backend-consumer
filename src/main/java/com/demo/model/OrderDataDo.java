package com.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDataDo implements Serializable {
    private String id;
    private Long timestamp;
    private Integer quantity;
    private String status;
    private Integer price;

    public OrderDataDo(String id, Long timestamp, Integer quantity, String status, Integer price) {
        this.id = id;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.status = status;
        this.price = price;
    }

//    // Getters and Setters
//    public String getId() { return id; }
//    public void setId(String id) { this.id = id; }
//    public Long getTimestamp() { return timestamp; }
//    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
//    public Integer getQuantity() { return quantity; }
//    public void setQuantity(Integer quantity) { this.quantity = quantity; }
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//    public Integer getPrice() { return price; }
//    public void setPrice(Integer price) { this.price = price; }
}