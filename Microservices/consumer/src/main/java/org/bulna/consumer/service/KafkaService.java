package org.bulna.consumer.service;

import org.bulna.consumer.config.AppConstants;
import org.bulna.consumer.dto.Course;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private String message;

    @KafkaListener(topics = AppConstants.COURSE_TOPIC_NAME, groupId = AppConstants.COURSE_GROUP_ID)
    public void consume(Course course){
        message = course + " Got the data from kafka";
        System.out.println(message);
    }

    @KafkaListener(topics = AppConstants.MESSAGE_TOPIC_NAME, groupId = AppConstants.MESSAGE_GROUP_ID)
    public void sub(String msg){
        message = msg + " Got the data from kafka";
        System.out.println(message);
    }

    public String getMessage() {
        return message;
    }
}
