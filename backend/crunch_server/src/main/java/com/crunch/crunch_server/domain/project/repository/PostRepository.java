package com.crunch.crunch_server.domain.project.repository;

import javax.transaction.Transactional;

import com.crunch.crunch_server.domain.project.entity.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
// @NoRepositoryBean
public interface PostRepository extends JpaRepository<Posts, Integer> {

     @Query(value = "SELECT * FROM posts p WHERE p.projectId = :projectId and p.indexId = :indexId", nativeQuery = true)
     Posts findByProjectIdAndIndexId(@Param("projectId") Integer projectId, @Param("indexId") Integer indexId);

     // this will generate after the index addition ui added
     @Modifying
     @Query(value = "INSERT INTO posts (indexId, projectId) SELECT :projectId, :indexId", nativeQuery = true)
     @Transactional
     void savePost(@Param("projectId") Integer projectId, @Param("indexId") Integer indexId);

}
