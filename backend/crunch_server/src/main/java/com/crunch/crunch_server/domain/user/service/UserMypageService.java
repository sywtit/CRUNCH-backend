package com.crunch.crunch_server.domain.user.service;

import com.crunch.crunch_server.domain.user.dto.UserMypageDTO;
import com.crunch.crunch_server.domain.user.dto.UserMypageUpdateDTO;
import com.crunch.crunch_server.domain.user.entity.Interest;
import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.mapper.InterestMapper;
import com.crunch.crunch_server.domain.user.respository.InterestRepository;
import com.crunch.crunch_server.domain.user.respository.UserRepository;
import com.crunch.crunch_server.s3.S3Uploader;
import com.crunch.crunch_server.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserMypageService {

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private S3Uploader s3Uploader;

    public UserMypageDTO getMyPageInfo(String token) {
        int userId = jwtUtil.getUserId(token);

        List<Interest> interest = interestRepository.findByUserId(userId);
        User user = userRepository.findById(userId);

        List<String> interestString=null;
        
        for(int i =0; i<interest.size(); i++)
        {
            interestString.add(interest.get(i).getText());
        }


        UserMypageDTO userMypageDTO = new UserMypageDTO();
        userMypageDTO.setInterest(interestString);
        userMypageDTO.setNickname(user.getNickname());
        userMypageDTO.setRecord(user.getRecord());
        userMypageDTO.setS3key(user.getS3key());

        return userMypageDTO;

    }

    public void updateMyPageInfo(String token, UserMypageUpdateDTO myPageUpdateInfo) throws IOException
    {
        int userId = jwtUtil.getUserId(token);
        List<String> interest = myPageUpdateInfo.getInterest();

        User user = userRepository.findById(userId);
        String s3key = s3Uploader.upload(myPageUpdateInfo.getImage(), "user");

        user.setS3key(s3key);
        user.setRecord(myPageUpdateInfo.getRecord());

        for(int i = 0; i<interest.size(); i++)
        {
            Interest interestEntity = InterestMapper.Instance.toInterestEntity(interest.get(i));
            interestEntity.setUser(user);

            interestRepository.save(interestEntity);
        }
        
    }

}
