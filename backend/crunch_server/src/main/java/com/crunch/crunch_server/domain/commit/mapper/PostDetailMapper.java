package com.crunch.crunch_server.domain.commit.mapper;

import com.crunch.crunch_server.domain.commit.entity.PostLineDetail;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostDetailMapper {
    
    PostDetailMapper Instance = Mappers.getMapper(PostDetailMapper.class);

    @Mapping(source="postId", target="postId")
    @Mapping(source="text", target="text")
    @Mapping(source="writerName", target = "writerName")
    @Mapping(source="lineNum", target="lineNum")
    PostLineDetail postDetailToEntity(Integer postId, String text, String writerName , Integer lineNum);


}
