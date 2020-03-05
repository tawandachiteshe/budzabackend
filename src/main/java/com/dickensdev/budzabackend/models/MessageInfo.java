package com.dickensdev.budzabackend.models;

import java.util.UUID;

public class MessageInfo {
    private UUID clientuuid;
    private String messageBody;
    private String date;
    private UserInfo userInfo;
    private MessageType messageType;
    private String Name;

    public MessageType getMessageType() {
        return messageType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageInfo(){}
    public MessageInfo(UUID clientuuid, String messageBody, String date, UserInfo userInfo) {
        this.clientuuid = clientuuid;
        this.messageBody = messageBody;
        this.date = date;
        this.userInfo = userInfo;
    }

    public UUID getClientuuid() {
        return clientuuid;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getDate() {
        return date;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
