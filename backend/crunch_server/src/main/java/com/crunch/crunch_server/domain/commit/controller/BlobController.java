package com.crunch.crunch_server.domain.commit.controller;

import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.commit.service.BlobService;
import com.crunch.crunch_server.domain.commit.service.ModifyService;
import com.crunch.crunch_server.domain.crew.dto.WriterCrewDetailDTO;
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

    @Autowired
    private ModifyService modifyService;

    ///project/{projectId}/blob/basicTool/{indexId}
    //get: for the blob text
    @CrossOrigin(origins="*")
    @GetMapping("/{projectId}/blob/basicTool/{indexId}")
    public BlobDTO showRecentPost(@PathVariable int projectId, @PathVariable int indexId)
    {
            int postId = postService.getPostID(projectId, indexId);
            Boolean checkModifying = modifyService.checkModifyingWhenReturnBlob(postId);
            
            boolean checkNewPost  = (service.getSizeOfCommitList(postId) == 0);

            if(checkNewPost && !checkModifying)
            {
                service.setPost_now(null);
                return null;
            }
            else if(!checkNewPost && !checkModifying)
            {
                recentCommitDTO = service.getRecentCommitInfo(postId);
                return service.getProjectBlob(recentCommitDTO);
            }
            else if(checkNewPost && checkModifying)
            {
                return service.getProjectBlobWhenNewPostAndModifyingNow(postId);
            }
            else
            {
                recentCommitDTO = service.getRecentCommitInfo(postId);
                return service.getProjectBlobWhenNotNewPostAndModifyingNow(recentCommitDTO, postId);
            }
        
    }

    //give writer crew
    @CrossOrigin(origins="*")
    @GetMapping("/{projectId}/writercrew")
    public List<WriterCrewDetailDTO> showWriterCrew(@PathVariable int projectId)
    {
        return service.getWriterCrewNameList(projectId);
    }
    
    //delete: the indexId project

}
