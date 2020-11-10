package com.crunch.crunch_server.domain.user.mapper;

import com.crunch.crunch_server.domain.user.dto.UserInfoDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoMapper {
    
    UserInfoMapper Instance = Mappers.getMapper(UserInfoMapper.class);

    UserInfoDTO toDTO(User user);
    
}
