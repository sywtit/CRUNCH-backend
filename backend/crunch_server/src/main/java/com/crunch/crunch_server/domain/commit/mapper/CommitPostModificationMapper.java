package com.crunch.crunch_server.domain.commit.mapper;

import com.crunch.crunch_server.domain.commit.entity.PostModification;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommitPostModificationMapper {
    
    CommitPostModificationMapper Instance = Mappers.getMapper(CommitPostModificationMapper.class);

    @Mapping(target = "commits", ignore = true)
    @Mapping(source="diffResult", target="diffResult")
    @Mapping(source="beforePostLength", target="beforePostLength")
    @Mapping(source="afterPostLength", target="afterPostLength")
    PostModification toModifiedPMEntity(String diffResult, int beforePostLength, int afterPostLength);

}
