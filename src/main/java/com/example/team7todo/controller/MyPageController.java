package com.example.team7todo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageController {

    @GetMapping("api/test")
    public void a () {
        System.out.println("도착");
    }
}
