package com.crunch.crunch_server.domain.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDTO {
    
    private int roomId;

    public static ChatRoomDTO create(int roomId)
    {
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
        chatRoomDTO.roomId = roomId;
        return chatRoomDTO;
    }
}
