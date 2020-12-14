package com.crunch.crunch_server.domain.community.controller;

import com.crunch.crunch_server.domain.community.dto.SocketDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
public class SocketController {
    
    // @MessageMapping("/receive")
    // @SendTo("/send")
    // public SocketDTO SocketHandler(SocketDTO socketDTO)
    // {
    //     SocketDTO newSocketMessage = socketDTO;
    //     return newSocketMessage;
        
    // }

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String roomId = (String) headerAccessor.getSessionAttributes().get("room_id");

        if(username != null) {
            logger.info("User Disconnected : " + username);

            // ChatMessage chatMessage = new ChatMessage();
            // chatMessage.setType(ChatMessage.MessageType.LEAVE);
            // chatMessage.setSender(username);

            // messagingTemplate.convertAndSend("/topic/public", chatMessage);
            //do nothing
        }
    }
}
