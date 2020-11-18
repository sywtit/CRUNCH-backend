package com.crunch.crunch_server.domain.commit.controller;

import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.CommitHistoryDTO;
import com.crunch.crunch_server.domain.commit.service.BlobService;
import com.crunch.crunch_server.domain.commit.service.HistoryService;
import com.crunch.crunch_server.domain.project.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/project")
public class CommitHistoryController {

    @Autowired
    private BlobService blobService;
    
    @Autowired
    private PostService postService;
    @Autowired
    private HistoryService service;
    
    //index's whole commit history
    @CrossOrigin(origins="*")
    @GetMapping("/{projectId}/commit/basicTool/{indexId}")
    public List<CommitHistoryDTO> showCommitList(@PathVariable int projectId, @PathVariable int indexId)
    {
        int postId = postService.getPostID(projectId, indexId);
        boolean checkNewPost = (blobService.getSizeOfCommitList(postId) == 0);

        if(checkNewPost)
        {
            return null;
        }
        else
        {
            return service.getHistoryList(postId);
        }

    }

    //commit history detail
    @CrossOrigin(origins = "*")
    @GetMapping("/{projectId}/commit/basicTool/detail/{commitId}")
    public String returnDiffResult(@PathVariable int projectId, @PathVariable int commitId)
    {
        return service.getDiffDetail(commitId);
    }
    

}
