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

    //input stream을 이용하거나 다른 방법으로 directory
    //위치를 알 수 있는 방법 조사
    public static String DEFAULT_USER_IMAGE = "../../../../../../../resources/static/img/default-user.jpg";
    // InputStream is = new BufferedInputStream(
    //     new FileInputStream(new ClassPathResource("img/default-user.png")));
    
    UserMapper Instance = Mappers.getMapper(UserMapper.class);
    
    //User 에서 id, picture, point필드 제외
    //password encode안했을때
    @Mapping(target = "id", constant = "0L")
    @Mapping(target =  "picture", defaultValue = DEFAULT_USER_IMAGE)
    @Mapping(target = "point", constant = "0L")
    User userDtoToEntity(UserDTO userDTO);

    //password encoding은 다음에 

    //user로 변환하면서 삭제되는 것은 없음
    UserDTO userToDto(User user);
}
