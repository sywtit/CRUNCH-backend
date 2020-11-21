package com.crunch.crunch_server.domain.project.repository;

import javax.transaction.Transactional;

import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.entity.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostIndexRepository extends JpaRepository<PostIndex, Integer> {

	PostIndex findByIdAndProjectId(int id, int projectId);

}
