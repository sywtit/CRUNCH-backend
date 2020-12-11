package com.crunch.crunch_server.domain.community.service;

import java.util.ArrayList;
import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.commit.dto.PostLineDetailDTO;
import com.crunch.crunch_server.domain.commit.dto.RecentCommitDTO;
import com.crunch.crunch_server.domain.commit.service.BlobService;
import com.crunch.crunch_server.domain.commit.service.ModifyService;
import com.crunch.crunch_server.domain.community.dto.BlobChatDTO;
import com.crunch.crunch_server.domain.community.dto.FirstCommunityBlobDTO;
import com.crunch.crunch_server.domain.community.dto.TagNameDTO;
import com.crunch.crunch_server.domain.community.entity.Chat;
import com.crunch.crunch_server.domain.community.mapper.ChatMapper;
import com.crunch.crunch_server.domain.community.repository.ChatRepository;
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

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatRoomService chatRoomService;

    
	public FirstCommunityBlobDTO getCommunityBlob(int projectId, int indexId) {

        FirstCommunityBlobDTO communityBlobDTO = new FirstCommunityBlobDTO();
        //first get the blob
        BlobDTO blobDTO = new BlobDTO();
        //if the indexId is 0 blob will be null
        if(indexId == 0) blobDTO = null;
        else blobDTO = getPostBlob(projectId, indexId);

        //set blob
        communityBlobDTO.setBlobDTO(blobDTO);

        //second get the roomId
        int communityId = chatRoomService.getRoomIdNumberPre(projectId, indexId);
        communityBlobDTO.setRoomId(communityId);

        //third get the chat list by communityId
        List<Chat> chatEntity = chatRepository.findAllByCommunityId(communityId);
        List<BlobChatDTO> chat = new ArrayList<BlobChatDTO>();

        int checkSameChatCount = 0;
        List<TagNameDTO> tagList = new ArrayList<TagNameDTO>();

        for(int i =0; i<chatEntity.size(); i++)
        {
            if(chatEntity.get(i).getSameChat() == 0)
            {
                checkSameChatCount = 0;
                tagList = new ArrayList<TagNameDTO>();
                TagNameDTO indexTagName = new TagNameDTO();
                indexTagName.setName(chatEntity.get(i).getTagNickname());
                tagList.add(indexTagName);
                chat.add(ChatMapper.Instance.toBlobChatDTO(chatEntity.get(i), tagList));
            }
            else
            {
                checkSameChatCount++;
                if(checkSameChatCount == 1)
                {
                    tagList = new ArrayList<TagNameDTO>();
                }
                if(checkSameChatCount<=chatEntity.get(i).getSameChat())
                {
                    TagNameDTO indexTagName = new TagNameDTO();
                    indexTagName.setName(chatEntity.get(i).getTagNickname());
                    tagList.add(indexTagName);

                    if(checkSameChatCount == chatEntity.get(i).getSameChat())
                    {
                        chat.add(ChatMapper.Instance.toBlobChatDTO(chatEntity.get(i), tagList));
                        checkSameChatCount = 0;
                    }
                }
            }


        }

        communityBlobDTO.setChat(chat);

        //last set writercrew
        communityBlobDTO.setWriterCrew(service.getWriterCrewNameList(projectId));
        return communityBlobDTO;
		
	}

    private BlobDTO getPostBlob(int projectId, int indexId) {
        BlobDTO blobDTO;
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
        return blobDTO;
    }
    
}
