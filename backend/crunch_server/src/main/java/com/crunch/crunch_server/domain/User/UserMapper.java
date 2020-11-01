package com.crunch.crunch_server.domain.User;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.FileInputStream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import lombok.Value;

@Mapper
public interface UserMapper {

    //input stream to get png location
    //public static String DEFAULT_USER_IMAGE = "../../../../../../../resources/static/img/default-user.jpg";
    // InputStream is = new BufferedInputStream(
    //     new FileInputStream(new ClassPathResource("img/default-user.png")));
    
    UserMapper Instance = Mappers.getMapper(UserMapper.class);
    
    //User : id,picture,point exception
    //with no password encoding
    @Mapping(target = "id", constant = "0L")
    @Mapping(target =  "picture", defaultValue = "")
    @Mapping(target = "point", constant = "0L")
    User userDtoToEntity(UserDTO userDTO);


    UserDTO userToDto(User user);
}
