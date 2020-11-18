package com.crunch.crunch_server.domain.commit.mapper;

import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.CommitHistoryDTO;
import com.crunch.crunch_server.domain.commit.entity.Commits;
import com.crunch.crunch_server.domain.crew.service.WriterCrewService;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.jsonwebtoken.io.IOException;

@Mapper
public interface HistroyCommitMapper {
    
    HistroyCommitMapper Instance = Mappers.getMapper(HistroyCommitMapper.class);

    default CommitHistoryDTO toHistoryDTO(Commits commit) {
        try {
            
            CommitHistoryDTO historyDTO = new CommitHistoryDTO();
            WriterCrewService writerCrewService = new WriterCrewService();

            historyDTO.setCommitId(commit.getCommitId());
            historyDTO.setCommit_comment(commit.getCommit_comment());
            historyDTO.setTime(commit.getTime());
            historyDTO.setWriterName(writerCrewService.getWriterName(commit.getUserId()));

            return historyDTO;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    List<CommitHistoryDTO> toHistoryListDTO(List<Commits> commit);  

}
