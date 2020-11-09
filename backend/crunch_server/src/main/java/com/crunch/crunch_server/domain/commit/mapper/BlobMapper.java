package com.crunch.crunch_server.domain.commit.mapper;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlobMapper {
    
    BlobMapper Instance = Mappers.getMapper(BlobMapper.class);

    BlobDTO toDTO(RecentCommitDTO recentCommitDTO);


    
}
