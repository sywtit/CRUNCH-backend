package com.crunch.crunch_server.domain.user.mapper;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;

import com.crunch.crunch_server.domain.user.dto.UserDTO;
import com.crunch.crunch_server.domain.user.entity.User;

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
    
    //User : id,picture,point,salt exception
    //with password encrypting
    @Mapping(target = "point", constant = "0")
    User toEntity(UserDTO userDTO);


}


