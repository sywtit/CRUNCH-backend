package com.crunch.crunch_server.domain.project.service;

import com.crunch.crunch_server.domain.project.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class PostService {
    
    @Autowired
    private PostRepository repository;


    //post
    public Integer getPostID(int projectId,  int indexId)
    {
       int postId = repository.findByProject_IdAndIndex_Id( projectId,  indexId);
       return postId;
        
    }

    
}
