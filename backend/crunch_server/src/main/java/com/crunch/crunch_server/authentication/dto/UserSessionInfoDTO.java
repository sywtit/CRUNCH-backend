package com.crunch.crunch_server.authentication.dto;

public class UserSessionInfoDTO {
    
    private String sender;

    /**
     * @return String return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
}
