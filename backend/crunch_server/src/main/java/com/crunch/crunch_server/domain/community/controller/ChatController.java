package com.crunch.crunch_server.domain.community.controller;

import com.crunch.crunch_server.domain.community.dto.SocketDTO;
import com.crunch.crunch_server.domain.community.service.ChatService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private ChatService chatService;
  
    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload SocketDTO chatMessage) {
      messagingTemplate.convertAndSend("/channel/"+ roomId, chatMessage);

      //have to save all
      chatService.saveChat(roomId, chatMessage);
    }
  
    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload SocketDTO chatMessage,
        SimpMessageHeaderAccessor headerAccessor) {
      String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
    //we don't leave
    //   if (currentRoomId != null) {
    //     ChatMessage leaveMessage = new ChatMessage();
    //     leaveMessage.setType(MessageType.LEAVE);
    //     leaveMessage.setSender(chatMessage.getSender());
    //     messagingTemplate.convertAndSend(format("/channel/%s", currentRoomId), leaveMessage);
    //   }
      headerAccessor.getSessionAttributes().put("username", chatMessage.getUserName());
      messagingTemplate.convertAndSend("/channel/"+ roomId, chatMessage);
    }

    
}

