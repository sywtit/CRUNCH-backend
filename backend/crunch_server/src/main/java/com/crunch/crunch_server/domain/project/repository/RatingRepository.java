package com.crunch.crunch_server.domain.project.repository;

import java.util.List;

import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.entity.Rating;
import com.crunch.crunch_server.domain.project.entity.RatingIdentity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingIdentity> {

    // Rating findByPostIdAndUserId(int id, int userId);

    Rating findByRatingIdentityPostIdAndRatingIdentityUserId(int id, int userId);

	List<Rating> findByRatingIdentityPostId(int id);

}
