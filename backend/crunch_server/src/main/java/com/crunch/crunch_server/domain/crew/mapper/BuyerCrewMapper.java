package com.crunch.crunch_server.domain.crew.mapper;

import com.crunch.crunch_server.domain.user.dto.UserPointDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BuyerCrewMapper {
    BuyerCrewMapper Instance = Mappers.getMapper(BuyerCrewMapper.class);

}
