package com.dickensdev.budzabackend.controllers;

import com.dickensdev.budzabackend.models.MessageInfo;
import com.dickensdev.budzabackend.models.UserInfo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ChatController {
    @MessageMapping("/api/v1/send")
    @SendTo("api/v1/topic/chat")
    public MessageInfo sendMessage(MessageInfo messageInfo, SimpMessageHeaderAccessor headerAccessor){
        messageInfo.setName(headerAccessor.getSessionAttributes().get("username").toString());
        return messageInfo;
    }
    @GetMapping("/api/v1/messaging")
    public String home(Model model, HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        if(username!=null) {
            return "redirect:/chat";
        }
        model.addAttribute("user", new UserInfo());
        return "login";
    }
    @PostMapping("/api/v1/messaging")
    public String homePost(@ModelAttribute UserInfo user, HttpSession httpSession) {
        if(!user.getUsername().isEmpty()) {
            httpSession.setAttribute("username", user.getUsername());
            return "redirect:/chat";
        }else {
            return "login";
        }
    }

    @GetMapping("/api/v1/messaging/chat")
    public String chat(HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        if(username!=null) {
            return "chat";
        }else {
            return "redirect:/";
        }
    }

    @PostMapping("/api/v1/messaging/logout")
    public String logout(HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        if(username!=null) {
            httpSession.removeAttribute("username");
        }
        return "redirect:/";
    }


}
