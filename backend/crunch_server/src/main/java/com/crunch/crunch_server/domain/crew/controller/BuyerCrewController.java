package com.crunch.crunch_server.domain.crew.controller;

import java.sql.Date;
import java.time.LocalDate;

import com.crunch.crunch_server.domain.crew.service.BuyerCrewService;
import com.crunch.crunch_server.domain.project.dto.PostFeeDTO;
import com.crunch.crunch_server.domain.project.dto.PostIndexDTO;
import com.crunch.crunch_server.domain.project.service.PostService;
import com.crunch.crunch_server.domain.user.service.UserService;
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
public class BuyerCrewController {
    @Autowired
    private PostService postService;

    @Autowired
    private BuyerCrewService buyerCrewService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "*")
    @PostMapping("/getfee")
    @ResponseStatus(value = HttpStatus.OK)
    public int getFee(@RequestHeader(value = "token") String token, @RequestBody PostIndexDTO postIndexDTO) {
        int userId = jwtUtil.getUserId(token);
        System.out.println(postIndexDTO.getId());
        return postService.getFee(postIndexDTO.getId(), postIndexDTO.getProjectId());
        // postindex 에 접근해서 postid, projectid, 가지고 fee 가져오기

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/minuspoint")
    @ResponseStatus(value = HttpStatus.OK)
    public int minusPoint(@RequestHeader(value = "token") String token, @RequestBody PostFeeDTO postFeeDTO) {
        System.out.println("sucess");
        int postIndex = postFeeDTO.getId();
        int projectId = postFeeDTO.getProjectId();
        int fee = postService.getFee(postIndex, projectId);
        int userId = 6;
        System.out.println("--------==========----1------=============----");
        int afterPoint = userService.minusPoint(userId, fee).getPoint();
        System.out.println(afterPoint);
        System.out.println("--------==========----2------=============----");
        // buyercrew에 insert
        // LocalDate currentDate = LocalDate.now();
        // Date.valueOf(LocalDate.now());

        buyerCrewService.addBuyerCrew(postIndex, projectId, userId, Date.valueOf(LocalDate.now()));

        return afterPoint;
    }
}
