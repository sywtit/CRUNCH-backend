package com.crunch.crunch_server.domain.commit.repository;

import com.crunch.crunch_server.domain.commit.entity.PostModification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifyPostModificationRepository extends JpaRepository<PostModification,Integer>
{
    
}
