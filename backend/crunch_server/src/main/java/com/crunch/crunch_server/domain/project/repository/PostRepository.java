package com.crunch.crunch_server.domain.project.repository;

import com.crunch.crunch_server.domain.project.entity.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts, Integer> {

    // @Query(value = "SELECT * FROM posts p WHERE p.projectId = :projectId and p.indexId = :indexId", nativeQuery = true)
     Posts findByProjectIdAndIndexId(@Param("projectId") Integer projectId, @Param("indexId") Integer indexId);
   // Posts findByProjectIdAndIndexId( Integer projectId,  Integer indexId);

}
