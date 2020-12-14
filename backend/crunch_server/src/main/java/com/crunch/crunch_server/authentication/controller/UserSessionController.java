package com.crunch.crunch_server.authentication.controller;

import com.crunch.crunch_server.authentication.dto.User;
import com.crunch.crunch_server.authentication.dto.UserSessionInfoDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserSessionController {
    private static final Logger logger = LoggerFactory.getLogger(UserSessionController.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/session/addUser")
    public void addUser(@Payload UserSessionInfoDTO userSessionInfo, SimpMessageHeaderAccessor headerAccessor) {

        headerAccessor.getSessionAttributes().put("username", userSessionInfo.getSender());
    //  messagingTemplate.convertAndSend("/channel/"+ roomId, chatMessage);
    }

    @SubscribeMapping("/queue")
        public void test(User p) { 
            // sendMessagesThatWereReceivedWhileUserWasOffline();
            System.out.println("7777777777");
        }

}
