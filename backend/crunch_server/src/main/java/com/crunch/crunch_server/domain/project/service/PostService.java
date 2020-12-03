package com.crunch.crunch_server.domain.project.service;

import java.util.ArrayList;
import java.util.List;

import com.crunch.crunch_server.domain.project.dto.IndexEditDTO;
import com.crunch.crunch_server.domain.project.dto.IndexTitleDTO;
import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.entity.PostIndexIdentity;
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

    // @Autowired
    // private

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

        PostIndex postIndex = postIndexRepository.findByPostIndexIdentityIdAndPostIndexIdentityProjectId(id, projectId);

        return postIndex.getFee();
    }

    public void setPostIndexEdit(List<IndexEditDTO> indexEditDTOs) {

        List<PostIndex> postIndexs = new ArrayList<PostIndex>();
        for (IndexEditDTO iDto : indexEditDTOs) {
            PostIndex pIndex = new PostIndex();
            PostIndexIdentity pIdentity = new PostIndexIdentity();

            pIdentity.setId(iDto.getIndexId());
            pIdentity.setProjectId(iDto.getProjectId());

            pIndex.setPostIndexIdentity(pIdentity);
            pIndex.setTitle(iDto.getTitle());
            postIndexs.add(pIndex);
        }

    }

    public void addLastPostIndex(IndexEditDTO indexEditDTO) {
        PostIndex postIndex = new PostIndex();

        System.out.println(indexEditDTO.getIndexId());

        // postIndex.setId(indexEditDTO.getIndexId());
        // postIndex.setProjectId(indexEditDTO.getProjectId());
        PostIndexIdentity pIdentity = new PostIndexIdentity();
        pIdentity.setId(indexEditDTO.getIndexId());
        pIdentity.setProjectId(indexEditDTO.getProjectId());

        postIndex.setPostIndexIdentity(pIdentity);
        postIndex.setTitle(indexEditDTO.getTitle());

        System.out.println("before postindex");

        // postIndexRepository.savePostIndex(indexEditDTO.getProjectId(),
        // indexEditDTO.getIndexId(),
        // indexEditDTO.getTitle());

        postIndexRepository.save(postIndex);

        System.out.println("after postindex");

        Posts post = new Posts();

        System.out.println("aaa");

        post.setIndex_id(indexEditDTO.getIndexId());

        System.out.println("bbb");

        post.setProject_id(indexEditDTO.getProjectId());

        System.out.println("ccc");

        // post.setModifying(0);
        // post.setModifyingUserId(-1);
        System.out.println("before post");
        repository.save(post);

    }

    public List<IndexTitleDTO> getIndexsOfProjectId(int id) {
        System.out.println("hihihihi");
        List<PostIndex> postIndexs = postIndexRepository.findByPostIndexIdentityProjectId(id);
        System.out.println("hellohello");
        List<IndexTitleDTO> iDtos = new ArrayList<IndexTitleDTO>();
        for (PostIndex pIndex : postIndexs) {
            IndexTitleDTO iDto = new IndexTitleDTO();
            iDto.setId(pIndex.getPostIndexIdentity().getId());
            iDto.setTitle(pIndex.getTitle());
            iDtos.add(iDto);
            System.out.println("-----------------------");
            System.out.println(iDto.getTitle());
            // postI

        }
        return iDtos;
    }

}
