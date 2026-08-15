package org.bulna.ecommorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcommOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommOrderServiceApplication.class, args);
    }

}
