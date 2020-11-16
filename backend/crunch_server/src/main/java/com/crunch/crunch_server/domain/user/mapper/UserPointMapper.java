package com.crunch.crunch_server.domain.user.mapper;

import com.crunch.crunch_server.domain.user.dto.UserPointDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPointMapper {
    UserPointMapper Instance = Mappers.getMapper(UserPointMapper.class);
    
    UserPointDTO toUserPointDTO(User user);
}
