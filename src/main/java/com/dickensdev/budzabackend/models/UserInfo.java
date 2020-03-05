package com.dickensdev.budzabackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;
@Component
public class UserInfo {
    private String username;
    private String email;
    private String password;
    private UUID userId;
    private UUID ConfirmationToken;
    private Boolean Enabled;
    private String roles;
    private String date;

    public UserInfo(){

    }
    public UserInfo(@JsonProperty("username") String username,@JsonProperty("email") String email,@JsonProperty("password") String password,@JsonProperty("userId") UUID userId, boolean enabled,String role,String date) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.Enabled = enabled;
        this.roles = role;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getConfirmationToken() {
        return ConfirmationToken;
    }

    public void setConfirmationToken(UUID confirmationToken) {
        ConfirmationToken = confirmationToken;
    }

    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean enabled) {
        Enabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}