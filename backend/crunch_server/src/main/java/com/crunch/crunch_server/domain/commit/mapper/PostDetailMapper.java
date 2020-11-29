package com.crunch.crunch_server.domain.commit.mapper;

import java.io.IOException;
import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.PostLineDetailDTO;
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

    default PostLineDetailDTO toPostDetailDTO(PostLineDetail postLineDetail)
    {
            PostLineDetailDTO lineDetailDTO = new PostLineDetailDTO();

            lineDetailDTO.setS3key(postLineDetail.getS3key());
            lineDetailDTO.setText(postLineDetail.getText());
            lineDetailDTO.setWriterName(postLineDetail.getWriterName());

            return lineDetailDTO;
    }

    List<PostLineDetailDTO> toPostDetailListDTO(List<PostLineDetail> postLineDetailList);
}
