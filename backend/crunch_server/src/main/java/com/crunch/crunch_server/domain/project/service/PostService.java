package com.crunch.crunch_server.domain.project.service;

import java.util.ArrayList;
import java.util.List;

import com.crunch.crunch_server.domain.project.dto.IndexEditDTO;
import com.crunch.crunch_server.domain.project.dto.IndexTitleDTO;
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

    public void setPostIndexEdit(List<IndexEditDTO> indexEditDTOs) {

        List<PostIndex> postIndexs = new ArrayList<PostIndex>();
        for (IndexEditDTO iDto : indexEditDTOs) {
            PostIndex pIndex = new PostIndex();
            pIndex.setId(iDto.getIndexId());
            pIndex.setProjectId(iDto.getProjectId());
            pIndex.setTitle(iDto.getTitle());
            postIndexs.add(pIndex);
        }

    }

    public void addLastPostIndex(IndexEditDTO indexEditDTO) {
        PostIndex postIndex = new PostIndex();
        postIndex.setId(indexEditDTO.getIndexId());
        postIndex.setProjectId(indexEditDTO.getProjectId());
        postIndex.setTitle(indexEditDTO.getTitle());

        postIndexRepository.save(postIndex);

    }

    public List<IndexTitleDTO> getIndexsOfProjectId(int id) {
        System.out.println("hihihihi");
        List<PostIndex> postIndexs = postIndexRepository.findByProjectId(id);
        List<IndexTitleDTO> iDtos = new ArrayList<IndexTitleDTO>();
        for (PostIndex pIndex : postIndexs) {
            IndexTitleDTO iDto = new IndexTitleDTO();
            iDto.setId(pIndex.getId());
            iDto.setTitle(pIndex.getTitle());
            iDtos.add(iDto);
            System.out.println("-----------------------");
            System.out.println(iDto.getTitle());
            // postI

        }
        return iDtos;
    }

}
