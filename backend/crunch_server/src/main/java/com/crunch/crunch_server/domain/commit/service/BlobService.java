package com.crunch.crunch_server.domain.commit.service;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.commit.entity.Commit;
import com.crunch.crunch_server.domain.commit.mapper.BlobMapper;
import com.crunch.crunch_server.domain.commit.mapper.CommitMapper;
import com.crunch.crunch_server.domain.commit.repository.BlobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlobService {
    
    @Autowired
    private BlobRepository repository;
    
    public BlobDTO getProjectBlob(RecentCommitDTO recentCommitDTO)
    {
        BlobDTO blobDTO = BlobMapper.Instance.toDTO(recentCommitDTO);
        return blobDTO;
    }

    public RecentCommitDTO getRecentCommitInfo(int postId)
    {
        List<Commit> commits = repository.findByPostId(postId);
        int last  = commits.size() -1;

        RecentCommitDTO commitDTO = CommitMapper.Instance.toRecentDTO(commits.get(last));
        return commitDTO;

        
    }

    
}
