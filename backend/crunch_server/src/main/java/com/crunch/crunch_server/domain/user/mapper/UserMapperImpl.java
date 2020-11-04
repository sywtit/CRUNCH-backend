package com.crunch.crunch_server.domain.user.mapper;

import javax.annotation.Generated;

import com.crunch.crunch_server.domain.user.EncryptionUtil;
import com.crunch.crunch_server.domain.user.dto.UserDTO;
import com.crunch.crunch_server.domain.user.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
public class UserMapperImpl implements UserMapper{
    
    @Override
    public User toEntity(UserDTO userDTO)
    {
        String salt = EncryptionUtil.generateSalt();
        
        User user = new User();
        user.setIdentity(userDTO.getIdentity());
        user.setName(userDTO.getName());
        user.setNickname(userDTO.getNickname());
        user.setGender(userDTO.getGender());
        
        user.setSalt(salt);
        user.setPassword(EncryptionUtil.getEncrypt(userDTO.getPassword(), salt.getBytes()));
        return user;
    }


    
}
