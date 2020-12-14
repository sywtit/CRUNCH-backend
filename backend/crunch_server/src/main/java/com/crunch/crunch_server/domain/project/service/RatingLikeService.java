package com.crunch.crunch_server.domain.project.service;

import java.util.List;

import com.crunch.crunch_server.domain.project.dto.RatingDTO;
import com.crunch.crunch_server.domain.project.entity.Posts;
import com.crunch.crunch_server.domain.project.entity.Rating;
import com.crunch.crunch_server.domain.project.entity.RatingIdentity;
import com.crunch.crunch_server.domain.project.repository.LikeRepository;
import com.crunch.crunch_server.domain.project.repository.PostRepository;
import com.crunch.crunch_server.domain.project.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingLikeService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    public boolean addrating(int userId, RatingDTO ratingDTO) {
        Posts post = postRepository.findByProjectIdAndIndexId(ratingDTO.getProjectId(), ratingDTO.getIndexId());
        Rating rating = ratingRepository.findByRatingIdentityPostIdAndRatingIdentityUserId(post.getId(), userId);
        if (rating == null) {
            Rating newRating = new Rating();
            RatingIdentity rIdentity = new RatingIdentity();
            rIdentity.setPostId(post.getId());
            rIdentity.setUserId(userId);
            newRating.setRatingIdentity(rIdentity);
            newRating.setRate(ratingDTO.getRate());
            newRating.setRateCount(1);
            ratingRepository.save(newRating);
            return true;
        } else {
            return false;
        }

    }

    public double getRatingOfProjectIdAndIndexId(int projectId, int postIndex) {
        Posts post = postRepository.findByProjectIdAndIndexId(projectId, postIndex);
        List<Rating> ratingList = ratingRepository.findByRatingIdentityPostId(post.getId());
        int ratingSum = 0;
        for (Rating rating : ratingList) {
            ratingSum = ratingSum + rating.getRate();
        }
        double ratingAvg = ratingSum / ratingList.size();
        System.out.println("Rating Average");
        System.out.println(ratingAvg);
        return ratingAvg;
    }

}
