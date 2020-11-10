package com.crunch.crunch_server.domain.user.mapper;

import javax.annotation.Generated;

import com.crunch.crunch_server.domain.user.dto.UserInfoDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
public class UserInfoMapperImpl implements UserInfoMapper{
    
    @Override
    public UserInfoDTO toUserInfoDTO(User user)
    {
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setGender(user.getGender());
        userInfoDTO.setIdentity(user.getIdentity());
        userInfoDTO.setName(user.getName());
        userInfoDTO.setNickname(user.getNickname());
        userInfoDTO.setPassword(user.getPassword());
        userInfoDTO.setPoint(user.getPoint());
        userInfoDTO.setRecord(user.getRecord());
        userInfoDTO.setS3key(user.getS3key());

        return userInfoDTO;


    }

}
