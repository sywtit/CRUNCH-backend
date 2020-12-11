package com.crunch.crunch_server.domain.user.respository;

import java.util.List;

import com.crunch.crunch_server.domain.user.entity.Interest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository  extends JpaRepository<Interest, Integer>{

	List<Interest> findByUserId(int userId);
    

}
