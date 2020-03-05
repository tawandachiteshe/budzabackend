package com.dickensdev.budzabackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("Messaging")
@Repository
public class Messaging {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Messaging(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    public String sendMessage(String message){
        System.out.println(message);
        return message;
    }

    public void deleteMessage(){

    }
    public void receiveMessage(){

    }


}
