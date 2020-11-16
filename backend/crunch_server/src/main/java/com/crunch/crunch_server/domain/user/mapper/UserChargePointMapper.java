package com.crunch.crunch_server.domain.user.mapper;

import com.crunch.crunch_server.domain.user.dto.ChargePointDTO;
// import com.crunch.crunch_server.domain.user.dto.UserPointDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserChargePointMapper {
    UserChargePointMapper Instance = Mappers.getMapper(UserChargePointMapper.class);

    // UserChargePointDTO toUserPointDTO(User user);

    // void toUserChargePointDTO(User user, int chargePoint);

    // UserPointMapper Instance = Mappers.getMapper(UserPointMapper.class);

    // UserPointDTO toUserPointDTO(User user);

    ChargePointDTO toUserChargePointDTO(User user);

   // void toEntityWithPoint(ChargePointDTO chargePointDTO);

}

// public class UserChargePoint {

// }
