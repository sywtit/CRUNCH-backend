package com.crunch.crunch_server.domain.commit.repository;

import com.crunch.crunch_server.domain.commit.entity.Commits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifyCommitRepoistory extends JpaRepository<Commits,Integer>
{

    @Query(value = "SELECT * FROM commits c WHERE c.id = :commitId", nativeQuery = true)
	Commits findByCommitId(@Param("commitId") int commitId);
    
}
