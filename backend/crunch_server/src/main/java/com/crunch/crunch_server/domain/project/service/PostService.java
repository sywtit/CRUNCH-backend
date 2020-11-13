package com.crunch.crunch_server.domain.project.service;

import com.crunch.crunch_server.domain.project.entity.Posts;
import com.crunch.crunch_server.domain.project.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    private PostRepository repository;


    //post
    public Integer getPostID(Integer projectId,  Integer indexId)
    {
       Posts post = repository.findByProjectIdAndIndexId( projectId,  indexId);
       int postId = post.getId();
    
       return postId;
        
    }

    
}
