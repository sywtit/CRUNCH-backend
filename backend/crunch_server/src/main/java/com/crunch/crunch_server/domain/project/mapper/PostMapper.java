package com.crunch.crunch_server.domain.project.mapper;

import com.crunch.crunch_server.domain.project.entity.Posts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    
    PostMapper Instance = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "projectId", target = "project_id")
    @Mapping(source = "indexId", target = "index_id")
    Posts toPostEntity(Integer projectId, Integer indexId);

}
