
package com.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DmsService {

    @Value("${dms.endpoint:http://localhost:8081/dms/orders}") // 你可以通过配置覆盖
    private String dmsEndpoint;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean pushOrder(Map<String, Object> orderData) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(orderData, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(dmsEndpoint, entity, String.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            // 可记录日志
            e.printStackTrace();
            return false;
        }
    }
}