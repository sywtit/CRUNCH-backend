package com.crunch.crunch_server.domain.commit.mapper;

import com.crunch.crunch_server.domain.commit.dto.CommitHistoryRevertDTO;
import com.crunch.crunch_server.domain.commit.dto.ModifyDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.commit.entity.Commits;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommitMapper  {

    CommitMapper Instance = Mappers.getMapper(CommitMapper.class);

    @Mapping(source = "commit.commitId", target = "id")
    RecentCommitDTO toRecentDTO(Commits commit);


    @Mapping(source="postId", target="postId")
    @Mapping(source="userId", target="userId")
    @Mapping(source="modifyDTO.after", target = "post")
    @Mapping(source="modifyDTO.time", target="time")
    @Mapping(source="modifyDTO.commit_comment", target="commit_comment")
    Commits toModifiedCommitsEntity(Integer postId, Integer userId, ModifyDTO modifyDTO);

    @Mapping(source="postId", target="postId")
    @Mapping(source="userId", target="userId")
    @Mapping(source="after", target = "post")
    @Mapping(source="chrDTO.time", target="time")
    @Mapping(source="chrDTO.commit_comment", target="commit_comment")
    Commits toHistoryCommitsEntity(Integer postId, Integer userId, String after, CommitHistoryRevertDTO chrDTO);

}
