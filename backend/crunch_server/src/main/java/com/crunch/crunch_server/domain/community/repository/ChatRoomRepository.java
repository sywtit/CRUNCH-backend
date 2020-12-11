package com.crunch.crunch_server.domain.community.repository;

import com.crunch.crunch_server.domain.community.entity.Community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<Community,Integer>{

	Community findByProjectIdAndPostindexId(int projectId, int indexId);

    
}
