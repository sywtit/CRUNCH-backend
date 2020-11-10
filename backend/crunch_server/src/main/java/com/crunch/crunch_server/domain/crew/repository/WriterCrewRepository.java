package com.crunch.crunch_server.domain.crew.repository;

import com.crunch.crunch_server.domain.crew.entity.WriterCrew;
import com.crunch.crunch_server.domain.crew.entity.WriterCrewIdentity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterCrewRepository extends JpaRepository<WriterCrew,WriterCrewIdentity>{

	WriterCrew findByWriterCrewIdentityUserId(int userId);
    
}
