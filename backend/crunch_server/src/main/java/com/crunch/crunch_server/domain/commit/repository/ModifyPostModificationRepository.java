package com.crunch.crunch_server.domain.commit.repository;

import com.crunch.crunch_server.domain.commit.entity.PostModification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifyPostModificationRepository extends JpaRepository<PostModification,Integer>
{

	@Query(value = "SELECT * FROM postmodification p WHERE p.commitId = :commitId", nativeQuery = true)
	PostModification findByCommitId(@Param("commitId") int commitId);

    
}
