package com.crunch.crunch_server.domain.community.service;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.commit.service.BlobService;
import com.crunch.crunch_server.domain.commit.service.ModifyService;
import com.crunch.crunch_server.domain.community.dto.FirstCommunityBlobDTO;
import com.crunch.crunch_server.domain.project.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {

    @Autowired
    private BlobService service;
    private RecentCommitDTO recentCommitDTO;

    @Autowired
    private PostService postService;

    @Autowired
    private ModifyService modifyService;
    
	public FirstCommunityBlobDTO getCommunityBlob(int projectId, int indexId) {

        //first get the blob
        BlobDTO blobDTO = new BlobDTO();
        //if the indexId is 0 blob will be null
        if(indexId == 0)
        blobDTO = null;

        else
        {
            int postId = postService.getPostID(projectId, indexId);
            Boolean checkModifying = modifyService.checkModifyingWhenReturnBlob(postId);
            
            boolean checkNewPost  = (service.getSizeOfCommitList(postId) == 0);

            if(checkNewPost && !checkModifying)
            {
                service.setPost_now(null);
                blobDTO =  null;
            }
            else if(!checkNewPost && !checkModifying)
            {
                recentCommitDTO = service.getRecentCommitInfo(postId);
                blobDTO =  service.getProjectBlob(recentCommitDTO);
            }
            else if(checkNewPost && checkModifying)
            {
                blobDTO =  service.getProjectBlobWhenNewPostAndModifyingNow(postId);
            }
            else
            {
                recentCommitDTO = service.getRecentCommitInfo(postId);
                blobDTO = service.getProjectBlobWhenNotNewPostAndModifyingNow(recentCommitDTO, postId);
            }
        }

		return null;
	}
    
}
