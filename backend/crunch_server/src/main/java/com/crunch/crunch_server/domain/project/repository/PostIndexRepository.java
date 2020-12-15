package com.crunch.crunch_server.domain.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.entity.PostIndexIdentity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostIndexRepository extends JpaRepository<PostIndex, PostIndexIdentity> {

	// PostIndex findByIdAndProjectId(int id, int projectId);

	PostIndex findByPostIndexIdentityIdAndPostIndexIdentityProjectId(int id, int projectId);

	// List<PostIndex> findByProjectId(int intValue);

	List<PostIndex> findByPostIndexIdentityProjectId(int intValue);


	// @Modifying
	// @Query(value = "INSERT INTO postindex (id, projectId, title) SELECT
	// :projectId, :indexId, :title", nativeQuery = true)
	// @Transactional
	// void savePostIndex(@Param("projectId") Integer projectId, @Param("indexId")
	// Integer indexId,
	// @Param("title") String title);

}
