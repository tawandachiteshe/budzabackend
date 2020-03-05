package com.dickensdev.budzabackend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String Hello(){
        return "index";
    }
    @GetMapping("/image")
    public String Image() {
        return "image";
    }
}
