package com.crunch.crunch_server.domain.user.controller;

import com.crunch.crunch_server.domain.user.entity.Account;
import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.respository.UserRepository;
import com.crunch.crunch_server.domain.user.service.AccountService;
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

import java.util.List;

import com.crunch.crunch_server.domain.user.dto.AccountDTO;
import com.crunch.crunch_server.domain.user.dto.ChargePointDTO;
import com.crunch.crunch_server.domain.user.dto.UserIdDTO;
import com.crunch.crunch_server.domain.user.dto.UserPointDTO;
import com.crunch.crunch_server.domain.user.entity.User;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "*")
    @PostMapping("/getaccounts")
    @ResponseStatus(value = HttpStatus.OK)
    public List<AccountDTO> getAllAccount(@RequestHeader(value = "token") String token,
            @RequestBody UserIdDTO userIdDTO) {
        System.out.println("heooollll");
        // ChargePointDTO chargePointDTO = new ChargePointDTO();
        // int id = chargePointDTO.getId();
        int userId = jwtUtil.getUserId(token);
        System.out.println("--------------------------------------------");
        System.out.println(userId);
        // // int id = 6;
        // List<AccountDTO> accounts =
        // System.out.println(accountService.getAllAccountsById(userId).get());

        return accountService.getAllAccountsById(userId);
        // return();

    }

}
