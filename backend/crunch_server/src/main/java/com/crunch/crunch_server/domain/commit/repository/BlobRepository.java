package com.crunch.crunch_server.domain.commit.repository;

import com.crunch.crunch_server.domain.commit.entity.Commit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlobRepository extends JpaRepository<Commit, Integer>{
    
}
