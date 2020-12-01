package com.crunch.crunch_server.domain.project.repository;

import java.util.List;

import com.crunch.crunch_server.domain.project.entity.PostIndex;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostIndexRepository extends JpaRepository<PostIndex, Integer> {

	PostIndex findByIdAndProjectId(int id, int projectId);

	List<PostIndex> findByProjectId(int intValue);

}
