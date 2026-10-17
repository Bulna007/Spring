package org.bulna.producer.controller;

import org.bulna.producer.config.AppConstants;
import org.bulna.producer.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        kafkaTemplate.send(AppConstants.ORDER_TOPIC_NAME, order.orderId(), order);
        return new ResponseEntity<>(Map.of("message","order posted successfully"), HttpStatus.OK);
    }
}
