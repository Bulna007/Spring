package org.bulna.producer.service;

import org.bulna.producer.config.AppConstants;
import org.bulna.producer.dto.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String, Course> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> template;

    public String sendMessage(Course course){
        kafkaTemplate.send(AppConstants.COURSE_TOPIC_NAME, null, course);
        return "Course message sent to kafka server";
    }

    public String publish(String msg){
        template.send(AppConstants.MESSAGE_TOPIC_NAME, msg);
        return msg + " Published by Spring BE Service";
    }
}
