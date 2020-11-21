package com.crunch.crunch_server.domain.user.mapper;

import java.util.List;

import com.crunch.crunch_server.domain.user.entity.Interest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterestMapper {
    
    InterestMapper Instance = Mappers.getMapper(InterestMapper.class);


    @Mapping(target = "user", ignore = true)
    @Mapping(target="text", source="text")
    Interest toInterestEntity(String text);

}
