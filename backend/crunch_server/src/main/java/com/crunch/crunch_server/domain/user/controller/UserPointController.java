package com.crunch.crunch_server.domain.user.controller;

import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.respository.UserRepository;
import com.crunch.crunch_server.domain.user.service.UserService;
import com.crunch.crunch_server.util.JwtUtil;

import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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

import com.crunch.crunch_server.domain.user.dto.ChargePointDTO;
import com.crunch.crunch_server.domain.user.dto.UserPointDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class UserPointController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "*")
    @GetMapping("/point")
    @ResponseStatus(value = HttpStatus.OK)
    public int point() {
        int id = 6;

        UserPointDTO userPointDTO = userService.getUserPointById(id);
        // System.out.println("Hello World!");
        System.out.println(userPointDTO.getPoint());
        return userPointDTO.getPoint();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/chargePoint")
    @ResponseStatus(value = HttpStatus.OK)
    public int pointCharge(@RequestHeader(value = "token") String token, @RequestBody ChargePointDTO chargePointDTO) {

        // ChargePointDTO chargePointDTO = new ChargePointDTO();
        // int id = chargePointDTO.getId();
        int userId = jwtUtil.getUserId(token);
        // int id = 6;
        User user = userService.addChargePoint(userId, chargePointDTO.getChargePoint());
        System.out.println(user.getPoint());

        // return();
        return user.getPoint();
    }

}
