package com.crunch.crunch_server.domain.user.respository;

import com.crunch.crunch_server.domain.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//entity, primary key
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

	User findByIdentity(String identity);

	User findById(int id);

	User findPointById(int id);

	@Query(value="SELECT * FROM user u WHERE u.id= :id", nativeQuery = true)
	User findByIdNumber(@Param("id") Integer Id);

}
