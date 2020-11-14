package com.crunch.crunch_server.domain.user.mapper;


import javax.annotation.Generated;

import com.crunch.crunch_server.domain.user.dto.UserPointDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)

public class UserPointMapperImpl implements UserPointMapper{
    
    @Override
    public UserPointDTO toUserPointDTO(User user)
    {
        UserPointDTO userPointDTO = new UserPointDTO();

        userPointDTO.setPoint(user.getPoint());

        return userPointDTO;
    }


}


