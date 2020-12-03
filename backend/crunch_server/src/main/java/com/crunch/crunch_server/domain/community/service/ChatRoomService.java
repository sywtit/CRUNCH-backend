package com.crunch.crunch_server.domain.community.service;

import java.util.List;

import com.crunch.crunch_server.domain.community.entity.Community;
import com.crunch.crunch_server.domain.community.repository.ChatRoomRepository;
import com.crunch.crunch_server.domain.crew.entity.WritersCrew;
import com.crunch.crunch_server.domain.crew.repository.WriterCrewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    
    @Autowired
    private WriterCrewRepository writerCrewRepository;

    
    public void makeSaveCommunity(int projectId, int indexId)
    {
            Community community = new Community();
            community.setPostindexId(indexId);
            community.setProjectId(projectId);

            chatRoomRepository.save(community);
    }


	public int getRoomIdNumberPre(int projectId, int indexId) {
        Community community = chatRoomRepository.findByProjectIdAndPostindexId(projectId, indexId);
		return community.getId();
	}
}
