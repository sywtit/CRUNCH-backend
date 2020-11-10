package com.crunch.crunch_server.domain.project.repository;

import com.crunch.crunch_server.domain.project.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    
   // @Query("select * from post where project_id= :projectId and index_id= :indexId")
    Post findByProjectIdAndIndexId(@Param("projectId") Integer projectId, @Param("indexId") Integer indexId);
}
