package com.crunch.crunch_server.domain.crew.mapper;

import com.crunch.crunch_server.domain.crew.dto.WriterCrewCheckDTO;
import com.crunch.crunch_server.domain.crew.entity.WriterCrew;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CheckMapper {
    
    CheckMapper Instance = Mappers.getMapper(CheckMapper.class);

    WriterCrewCheckDTO toDTO(WriterCrew writerCrew);
}
