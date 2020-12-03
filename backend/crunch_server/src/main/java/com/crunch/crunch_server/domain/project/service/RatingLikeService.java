package com.crunch.crunch_server.domain.project.service;

import com.crunch.crunch_server.domain.project.dto.RatingDTO;
import com.crunch.crunch_server.domain.project.entity.Rating;
import com.crunch.crunch_server.domain.project.repository.LikeRepository;
import com.crunch.crunch_server.domain.project.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingLikeService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private LikeRepository likeRepository;

    public void addrating(int userId, RatingDTO ratingDTO) {
        Rating rating = new Rating();
        rating.setPostId(ratingDTO.getPostId());
        rating.setRate(ratingDTO.getRate());
        rating.setUserId(userId);

        ratingRepository.save(rating);

    }

}
