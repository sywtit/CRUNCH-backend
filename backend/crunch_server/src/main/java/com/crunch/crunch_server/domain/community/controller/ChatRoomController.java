package com.crunch.crunch_server.domain.community.controller;

import com.crunch.crunch_server.domain.community.service.ChatRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ChatRoomController {


    @Autowired
    private ChatRoomService chatRoomService;

    //make chat romm(community)
    @CrossOrigin(origins="*")
    @GetMapping("/{projectId}/index/{indexId}/makeChatRoom")
    public void makeChatCommunity(@PathVariable int projectId, @PathVariable int indexId)
    {
        chatRoomService.makeSaveCommunity(projectId, indexId);
    }

    //when get in the chatroom
    //give room id
    @CrossOrigin(origins="*")
    @GetMapping("/{projectId}/index/{indexId}/roomId")
    public int getRoomId(@PathVariable int projectId, @PathVariable int indexId)
    {
        return chatRoomService.getRoomIdNumberPre(projectId, indexId);
    }

}
