package com.crunch.crunch_server.domain.commit.repository;

import java.util.List;

import com.crunch.crunch_server.domain.commit.entity.Commits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlobRepository extends JpaRepository<Commits, Integer>{

	List<Commits> findByPostId(int postId);


}
