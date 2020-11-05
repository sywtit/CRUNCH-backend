package com.crunch.crunch_server.domain.user.mapper;

import com.crunch.crunch_server.domain.user.dto.SessionRequestDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SessionLoginMapper {
    SessionLoginMapper Instance = Mappers.getMapper(SessionLoginMapper.class);
    
    SessionRequestDTO toDto(User user);
}
