package com.crunch.crunch_server.domain.commit.mapper;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlobMapper {
    
    BlobMapper Instance = Mappers.getMapper(BlobMapper.class);

    @Mapping(source = "user.name", target = "writerName")
    @Mapping(source = "recentCommitDTO.s3key", target = "s3key")
    BlobDTO toDTO(RecentCommitDTO recentCommitDTO, User user);


    
}
