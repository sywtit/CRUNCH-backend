package com.crunch.crunch_server.domain.commit.mapper;

import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.PostLineDetailDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlobMapper {
    
    BlobMapper Instance = Mappers.getMapper(BlobMapper.class);

    @Mapping(source = "user.nickname", target = "writerName")
    @Mapping(source = "recentCommitDTO.s3key", target = "s3key")
    @Mapping(target="modifying", constant = "false")
    @Mapping(target="postDetailList", source="postLineDetailList")
    BlobDTO toDTO(RecentCommitDTO recentCommitDTO, User user, List<PostLineDetailDTO> postLineDetailList);

    @Mapping(target="modifying", constant = "true")
    @Mapping(target="hisNickname", source = "otherUser.nickname")
    @Mapping(target="hisS3key", source = "otherUser.s3key")
    BlobDTO toAddUserDTO(User otherUser);

    @Mapping(source = "user.nickname", target = "writerName")
    @Mapping(source = "recentCommitDTO.s3key", target = "s3key")
    @Mapping(target="modifying", constant = "true")
    @Mapping(target="hisNickname", source = "otherUser.nickname")
    @Mapping(target="hisS3key", source = "otherUser.s3key")
    @Mapping(target="postDetailList", source="postLineDetailList")
    BlobDTO toAddModifyingUserDTO(RecentCommitDTO recentCommitDTO, User user, User otherUser,List<PostLineDetailDTO> postLineDetailList);

    
}
