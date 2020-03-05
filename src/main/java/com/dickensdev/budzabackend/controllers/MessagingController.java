package com.dickensdev.budzabackend.controllers;

import com.dickensdev.budzabackend.services.Messaging;
import com.dickensdev.budzabackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class MessagingController {
    private final Messaging messaging;
    private final UserService userService;
    @Autowired
    public MessagingController(UserService userService , Messaging messaging) {
        this.userService = userService;
        this.messaging = messaging;
    }

    @PostMapping("/send/{message}")
    public String sendMessage(@RequestBody String message){
        messaging.sendMessage(message);
        return "success";
    }
}
