package com.luzzetti.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class FunRestController {

    @Value("${dev.name}")
    private String devName;

    @Value("${dev.mail}")
    private String devMail;

    //  expose a "/" endpoint that return "Hello World"
    @GetMapping("/")
    public String sayHello() {
        System.out.println("Props read from " + devName + "!");
        return "Hello World! Time on server (Italy) is " + LocalDateTime.now() + devMail;
    }

    // expose a new endpoint for "workout"

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Corri un'oretta o due!";
    }

    @GetMapping("/workout2")
    public String getDailyWorkout2() {
        return "Corri anche tre ore dai!";
    }



}
