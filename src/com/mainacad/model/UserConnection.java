package com.mainacad.model;

import com.mainacad.service.FileService;

import java.util.Date;

public class UserConnection {
    private String sessionId;
    private Long connectionTime;
    private String userIP;

    public UserConnection() {
    }

    public UserConnection(String userIP, String sessionId, Long connectionTime) {
        this.userIP = userIP;
        this.sessionId = sessionId;
        this.connectionTime = connectionTime;
    }

    @Override
    public String toString() {
        Date date = new Date();
        date.setTime(connectionTime);
        return "sessionId: " + sessionId + "  connection time: " + date.toString() + "  userIP: " + userIP;
    }

}
