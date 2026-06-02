package org.example.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @GetMapping("/health")
    @PreAuthorize("hasRole('USER')")
    public String getHealth(){
        return "Get healthy";
    }

    @PostMapping("/health")
    @PostAuthorize("hasAuthority('USER_READ')")
    public String createHealth(){
        return "Post healthy";
    }

    @DeleteMapping("/health")
    public String deleteHealth(){
        return "Delete healthy";
    }

}
