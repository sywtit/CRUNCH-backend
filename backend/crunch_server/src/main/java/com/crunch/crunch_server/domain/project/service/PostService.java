package com.crunch.crunch_server.domain.project.service;

import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.entity.Posts;
import com.crunch.crunch_server.domain.project.mapper.PostMapper;
import com.crunch.crunch_server.domain.project.repository.PostIndexRepository;
import com.crunch.crunch_server.domain.project.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private PostIndexRepository postIndexRepository;

    // post
    public Integer getPostID(Integer projectId, Integer indexId) {

        int postId = 0;
        try {
            Posts post = repository.findByProjectIdAndIndexId(projectId, indexId);
            postId = post.getId();
            return postId;
        } catch (NullPointerException e) {
            // do nothing
            return postId;

        }

    }

    public int getFee(int id, int projectId) {

        PostIndex postIndex = postIndexRepository.findByIdAndProjectId(id, projectId);

        return postIndex.getFee();
    }

    // public void saveNewPost(Integer projectId, Integer indexId)
    // {
    // Posts post = PostMapper.Instance.toPostEntity(projectId, indexId);
    // repository.savePost(projectId, indexId);

    // }

}
