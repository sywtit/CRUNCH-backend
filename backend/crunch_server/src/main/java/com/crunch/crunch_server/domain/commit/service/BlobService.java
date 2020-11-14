package com.crunch.crunch_server.domain.commit.service;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.commit.entity.Commits;
import com.crunch.crunch_server.domain.commit.mapper.BlobMapper;
import com.crunch.crunch_server.domain.commit.mapper.CommitMapper;
import com.crunch.crunch_server.domain.commit.repository.BlobRepository;
import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
public class BlobService {
    
    @Autowired
    private BlobRepository repository;

    @Autowired
    private UserService service;
    private User user;
    

    public BlobDTO getProjectBlob(RecentCommitDTO recentCommitDTO)
    {
        user = service.getUserById(recentCommitDTO.getUserId());

        BlobDTO blobDTO = BlobMapper.Instance.toDTO(recentCommitDTO,user);
        return blobDTO;
    }

    public RecentCommitDTO getRecentCommitInfo(int postId)
    {
        List<Commits> commits = repository.findByPostId(postId);
        int last  = commits.size() -1;

        RecentCommitDTO commitDTO = CommitMapper.Instance.toRecentDTO((Commits)commits.get(last));
        return commitDTO;
        
    }

    
}
