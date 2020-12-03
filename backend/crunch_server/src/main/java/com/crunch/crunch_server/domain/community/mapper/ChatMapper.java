package com.crunch.crunch_server.domain.community.mapper;

import com.crunch.crunch_server.domain.community.dto.BlobChatDTO;
import com.crunch.crunch_server.domain.community.dto.SocketDTO;
import com.crunch.crunch_server.domain.community.entity.Chat;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMapper {
    
    ChatMapper Instance = Mappers.getMapper(ChatMapper.class);

    @Mapping(target="userNickname", source="message.userName")
    @Mapping(target="text", source="message.content")
    @Mapping(target="time", source="message.time")
    Chat toChatEntity(String communityId, String tagNickname, SocketDTO message);

    BlobChatDTO toBlobChatDTO(Chat chat);
    
}
