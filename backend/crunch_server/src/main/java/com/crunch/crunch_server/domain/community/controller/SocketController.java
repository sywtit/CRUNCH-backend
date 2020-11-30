package com.crunch.crunch_server.domain.community.controller;

import com.crunch.crunch_server.domain.community.dto.SocketDTO;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    
    @MessageMapping("/receive")
    @SendTo("/send")
    public SocketDTO SocketHandler(SocketDTO socketDTO)
    {
        SocketDTO newSocketMessage = socketDTO;
        return newSocketMessage;
        
    }
}
