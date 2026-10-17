package org.bulna.producer.controller;

import org.bulna.producer.dto.Course;
import org.bulna.producer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/add-course")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        String response = kafkaService.sendMessage(course);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<String> addCourse(@RequestParam String msg){
        String response = kafkaService.publish(msg);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
