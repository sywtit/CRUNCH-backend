package com.crunch.crunch_server.domain.project.controller;

import java.util.List;

import com.crunch.crunch_server.domain.project.dto.CommentDTO;
import com.crunch.crunch_server.domain.project.entity.Comment;
import com.crunch.crunch_server.domain.project.dto.ProjectIndexUserDTO;
import com.crunch.crunch_server.domain.project.dto.RatingDTO;
import com.crunch.crunch_server.domain.project.service.CommentService;
import com.crunch.crunch_server.domain.project.service.RatingLikeService;
import com.crunch.crunch_server.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RatingLikeController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RatingLikeService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/addRating")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean addRating(@RequestHeader(value = "token") String token, @RequestBody RatingDTO ratingDTO) {

        int userId = jwtUtil.getUserId(token);
        return service.addrating(userId, ratingDTO);

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getAvgRating")
    @ResponseStatus(value = HttpStatus.OK)
    public double getRating(@RequestHeader(value = "token") String token,
            @RequestBody ProjectIndexUserDTO pIndexUserDTO) {

        int userId = jwtUtil.getUserId(token);
        return service.getRatingOfProjectIdAndIndexId(pIndexUserDTO.getProjectId(), pIndexUserDTO.getPostIndex());

    }
}
