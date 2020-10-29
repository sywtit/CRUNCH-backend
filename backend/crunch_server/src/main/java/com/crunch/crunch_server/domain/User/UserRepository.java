package com.crunch.crunch_server.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

//entity, primary key
public interface UserRepository extends JpaRepository<User,Integer>{

	User findByName(String name);
    
}
