package org.bulna.consumer.controller;

import org.bulna.consumer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/get-course")
    public ResponseEntity<String> getCourse(){
        String response = kafkaService.getMessage();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<String> consCourse(){
        String response = kafkaService.getMessage();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
