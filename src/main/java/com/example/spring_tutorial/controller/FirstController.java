package com.example.spring_tutorial.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
@RestController

public class FirstController {
    @GetMapping("/")
    public String first(){
        return "これはRestコントローラーからのメッセージです";
    }
}
