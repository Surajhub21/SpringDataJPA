package com.jpa.springdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HTMLViewController {

    @GetMapping("/")
    public String healthCheck(){
        return "home";
    }

}
