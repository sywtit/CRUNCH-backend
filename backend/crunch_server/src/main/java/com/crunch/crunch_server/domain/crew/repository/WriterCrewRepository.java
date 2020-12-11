package com.crunch.crunch_server.domain.crew.repository;

import java.util.List;

import com.crunch.crunch_server.domain.crew.entity.WriterCrewIdentity;
import com.crunch.crunch_server.domain.crew.entity.WritersCrew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterCrewRepository extends JpaRepository<WritersCrew, WriterCrewIdentity> {

	// WritersCrew findByWriterCrewIdentityUserId(int userId);

	WritersCrew findByWriterCrewIdentityUserIdAndWriterCrewIdentityProjectId(int userId, int id);

	List<WritersCrew> findByWriterCrewIdentityProjectId(int project_id);

	List<WritersCrew> findByWriterCrewIdentityUserId(int userId);

}
