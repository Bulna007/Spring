package org.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/health")
    public String getHealth(){
        return "Get healthy";
    }

    @PostMapping("/health")
    public String createHealth(){
        return "Post healthy";
    }

    @DeleteMapping("/health")
    public String deleteHealth(){
        return "Delete healthy";
    }

}
