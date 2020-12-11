package com.crunch.crunch_server.domain.community.mapper;

import java.util.List;

import com.crunch.crunch_server.domain.community.dto.BlobChatDTO;
import com.crunch.crunch_server.domain.community.dto.SocketDTO;
import com.crunch.crunch_server.domain.community.dto.TagNameDTO;
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
    @Mapping(target="sameChat", source = "tagSize")
    Chat toChatEntity(String communityId, String tagNickname, SocketDTO message, Integer tagSize);

    @Mapping(target="tagNickname", source="tagList")
    BlobChatDTO toBlobChatDTO(Chat chat, List<TagNameDTO> tagList);
    
}
