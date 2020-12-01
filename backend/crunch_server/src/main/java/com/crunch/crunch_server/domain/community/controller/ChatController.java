package com.crunch.crunch_server.domain.community.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {
    
    private final SimpMessageSendingOperations messagingTemplate;

    // @MessageMapping("/chat/message")
    // public void message(ChatMessage message) {
    //     if (ChatMessage.MessageType.JOIN.equals(message.getType()))
    //         message.setMessage(message.getSender() + "¥‘¿Ã ¿‘¿Â«œºÃΩ¿¥œ¥Ÿ.");
    //     messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    // }
    
}

