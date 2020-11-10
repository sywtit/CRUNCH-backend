package com.crunch.crunch_server.domain.commit.mapper;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.service.UserService;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
public class BlobMapperImpl implements BlobMapper{
    
    @Autowired
    private UserService service;
    private User user;

    @Override
    public  BlobDTO toDTO(RecentCommitDTO recentCommitDTO)
    {
        BlobDTO blobDTO = new BlobDTO();
        blobDTO.setCommit_comment(recentCommitDTO.getCommit_comment());
        blobDTO.setPost(recentCommitDTO.getPost());
        blobDTO.setS3key(recentCommitDTO.getS3key());
        blobDTO.setTime(recentCommitDTO.getTime());

        user = service.getUserById(recentCommitDTO.getUserId());
        String username = user.getName();

        blobDTO.setWriterName(username);
        return blobDTO;

    }

}
