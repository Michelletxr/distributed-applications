package com.br.balancer.loadbalancer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/balancer")
public class BalancerController {
    
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/library")
    public ResponseEntity getDataLibrary(){
        return restTemplate.getForEntity("http://LIBRARY-MAIN/api/lib", String.class);
    }

    @GetMapping("/auth")
    public ResponseEntity getDataAuth(){
        return restTemplate.getForEntity("http://AUTH/api/auth/load", String.class);
    }

    @GetMapping("/notification")
    public ResponseEntity getDataNotification(){
        return restTemplate.getForEntity("http://SERVICE-NOTIFICATION/api/notifications", String.class);
    }

}
