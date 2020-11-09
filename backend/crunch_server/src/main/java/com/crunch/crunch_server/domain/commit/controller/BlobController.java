package com.crunch.crunch_server.domain.commit.controller;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.commit.entity.Commit;
import com.crunch.crunch_server.domain.commit.service.BlobService;

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
    ///project/{projectId}/blob/basicTool/{indexId}
    //get: for the blob text
    @CrossOrigin(origins="*")
    @GetMapping("/{projectId}/blob/basicTool/{indexId}")
    public BlobDTO showRecentPost(@PathVariable int projectId, @PathVariable int indexId)
    {
        recentCommitDTO = service.getRecentCommitInfo(projectId, indexId);
        return service.getProjectBlob(recentCommitDTO);
    }
    
    //delete: the indexId project


}
