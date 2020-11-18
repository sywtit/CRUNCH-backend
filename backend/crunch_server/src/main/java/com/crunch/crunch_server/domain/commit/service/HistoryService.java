package com.crunch.crunch_server.domain.commit.service;

import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.CommitHistoryDTO;
import com.crunch.crunch_server.domain.commit.entity.Commits;
import com.crunch.crunch_server.domain.commit.entity.PostModification;
import com.crunch.crunch_server.domain.commit.mapper.HistroyCommitMapper;
import com.crunch.crunch_server.domain.commit.repository.BlobRepository;
import com.crunch.crunch_server.domain.commit.repository.HistoryCommitDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    
    @Autowired
    private BlobRepository blobRepository;

    @Autowired
    private HistoryCommitDetailRepository detailRepository;

    public List<CommitHistoryDTO> getHistoryList(int postId)
    {
        List<Commits> commitList = blobRepository.findByPostId(postId);
        return HistroyCommitMapper.Instance.toHistoryListDTO(commitList);
    }

    public String getDiffDetail(int commitId)
    {
       PostModification postModification = detailRepository.findByCommitId(commitId);
       return postModification.getDiffResult();
    }

}
