package com.crunch.crunch_server.domain.commit.mapper;

import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.commit.entity.Commit;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommitMapper {

    CommitMapper Instance = Mappers.getMapper(CommitMapper.class);

    RecentCommitDTO toRecentDTO(Commit commit);

}
