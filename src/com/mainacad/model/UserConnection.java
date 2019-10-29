package com.mainacad.model;

import java.io.Serializable;
import java.util.Date;

public class UserConnection implements Serializable {
    private Integer sessionId;
    private Long connectionTime;
    private String userIP;

    public UserConnection() {
    }

    public UserConnection(String userIP, Integer sessionId, Long connectionTime) {
        this.userIP = userIP;
        this.sessionId = sessionId;
        this.connectionTime = connectionTime;
    }

    @Override
    public String toString() {
        Date date = new Date();
        date.setTime(connectionTime);
        return sessionId + " " + connectionTime + " " + userIP;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Long getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(Long connectionTime) {
        this.connectionTime = connectionTime;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }
}
