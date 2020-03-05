package com.dickensdev.budzabackend.config;

import com.dickensdev.budzabackend.models.MessageInfo;
import com.dickensdev.budzabackend.models.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    @Autowired
    SimpMessageSendingOperations sendingOperations;
    @EventListener
    public void handleWebsocketDisconnect(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null){
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setMessageType(MessageType.LEAVE);
            messageInfo.setName(username);
            sendingOperations.convertAndSend("/topic/chat",messageInfo);
        }
    }

}
