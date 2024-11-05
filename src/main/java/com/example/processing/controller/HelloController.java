package com.example.processing.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
    public String hello() {
        return "Hello";
    }
}
