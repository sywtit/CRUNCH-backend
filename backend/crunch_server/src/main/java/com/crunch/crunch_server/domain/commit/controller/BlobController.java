package com.crunch.crunch_server.domain.commit.controller;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.commit.service.BlobService;
import com.crunch.crunch_server.domain.crew.service.WriterCrewService;
import com.crunch.crunch_server.domain.project.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/project")
public class BlobController {

    @Autowired
    private BlobService service;
    private RecentCommitDTO recentCommitDTO;

    @Autowired
    private PostService postService;

    ///project/{projectId}/blob/basicTool/{indexId}
    //get: for the blob text
    @CrossOrigin(origins="*")
    @GetMapping("/{projectId}/blob/basicTool/{indexId}")
    public BlobDTO showRecentPost(@PathVariable int projectId, @PathVariable int indexId)
    {
            int postId = postService.getPostID(projectId, indexId);
            boolean checkNewPost  = (service.getSizeOfCommitList(postId) == 0);

            if(checkNewPost)
            {
                service.setPost_now(null);
                return null;
            }
            else
            {
                recentCommitDTO = service.getRecentCommitInfo(postId);
                return service.getProjectBlob(recentCommitDTO);
            }
        
    }
    
    //delete: the indexId project

}
