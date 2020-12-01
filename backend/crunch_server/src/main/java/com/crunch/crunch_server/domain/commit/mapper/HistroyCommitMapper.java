package com.crunch.crunch_server.domain.commit.mapper;

import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.CommitHistoryDTO;
import com.crunch.crunch_server.domain.commit.entity.Commits;

import com.crunch.crunch_server.domain.user.respository.UserRepository;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import io.jsonwebtoken.io.IOException;

@Mapper
public interface HistroyCommitMapper {

    HistroyCommitMapper Instance = Mappers.getMapper(HistroyCommitMapper.class);

    default CommitHistoryDTO toHistoryDTO(Commits commit, @Context UserRepository userRepository) {
        try {

            CommitHistoryDTO historyDTO = new CommitHistoryDTO();

            historyDTO.setCommitId(commit.getCommitId());
            historyDTO.setCommit_comment(commit.getCommit_comment());
            historyDTO.setTime(commit.getTime());
            historyDTO.setWriterName(userRepository.findByIdNumber(commit.getUserId()).getNickname());
            return historyDTO;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    List<CommitHistoryDTO> toHistoryListDTO(List<Commits> commit, @Context UserRepository userRepository);

}
