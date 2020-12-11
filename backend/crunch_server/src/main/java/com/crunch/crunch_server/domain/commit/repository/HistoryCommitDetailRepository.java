package com.crunch.crunch_server.domain.commit.repository;

import com.crunch.crunch_server.domain.commit.entity.PostModification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryCommitDetailRepository extends JpaRepository<PostModification,Integer>{

    // @Query(value = "SELECT * FROM posts p WHERE p.projectId = :projectId and p.indexId = :indexId", nativeQuery = true)
    // Posts findByProjectIdAndIndexId(@Param("projectId") Integer projectId, @Param("indexId") Integer indexId);

    @Query(value = "SELECT * FROM postmodification p WHERE p.commitId = :commitId", nativeQuery = true)
	PostModification findByCommitId(@Param("commitId") int commitId);

    
}
