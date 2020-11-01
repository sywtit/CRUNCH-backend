package com.crunch.crunch_server.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//entity, primary key
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	User findByName(String name);
    
}
