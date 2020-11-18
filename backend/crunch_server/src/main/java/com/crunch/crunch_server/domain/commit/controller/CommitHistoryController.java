package com.crunch.crunch_server.domain.commit.controller;

import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.CommitHistoryDTO;
import com.crunch.crunch_server.domain.commit.dto.CommitHistoryRevertDTO;
import com.crunch.crunch_server.domain.commit.service.BlobService;
import com.crunch.crunch_server.domain.commit.service.HistoryService;
import com.crunch.crunch_server.domain.commit.service.ModifyService;
import com.crunch.crunch_server.domain.project.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @Autowired
    private ModifyService modifyService;
    
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

    //commit history reverse
    @CrossOrigin(origins="*")
    @PostMapping("/{projectId}/commit/basicTool/detail/revert/{commitId}")
    @ResponseStatus(value=HttpStatus.OK)
    void saveRevertDiffResult(
        @RequestHeader(value="token") String token,
        @PathVariable int projectId, @PathVariable int commitId,
        CommitHistoryRevertDTO chrDTO) throws Exception
    {
        modifyService.saveNewCommitWithHistory(token, projectId, commitId, chrDTO);
    }
    


    

}
