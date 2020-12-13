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

import java.util.ArrayList;
import java.util.List;

import com.crunch.crunch_server.domain.crew.service.BuyerCrewService;
import com.crunch.crunch_server.domain.project.dto.ProjectIdDTO;
import com.crunch.crunch_server.domain.user.dto.AccountDTO;
import com.crunch.crunch_server.domain.user.dto.ChargePointDTO;
import com.crunch.crunch_server.domain.user.dto.EachIndexProfitDTO;
import com.crunch.crunch_server.domain.user.dto.EachMonthProfitDTO;
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
    private BuyerCrewService buyerCrewService;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "*")
    @PostMapping("/getaccounts")
    @ResponseStatus(value = HttpStatus.OK)
    public List<AccountDTO> getAllAccount(@RequestHeader(value = "token") String token,
            @RequestBody UserIdDTO userIdDTO) {

        int userId = jwtUtil.getUserId(token);
        return accountService.getAllAccountsById(userId);

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/submitaccount")
    @ResponseStatus(value = HttpStatus.OK)
    public int addAccount(@RequestHeader(value = "token") String token, @RequestBody AccountDTO accountDTO) {

        int userId = jwtUtil.getUserId(token);
        accountService.addnewAccount(accountDTO, userId);

        return 100;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getEachMonthOfMyProfit")
    @ResponseStatus(value = HttpStatus.OK)
    public List<List<EachMonthProfitDTO>> getEachMonthOfMyProfit(@RequestHeader(value = "token") String token,
            @RequestBody UserIdDTO userIdDTO) {
        int userId = jwtUtil.getUserId(token);
        // userId로 프로젝트 목록 가져오고
        // 가져온 프로젝트 별로 목차, 내 정산 비율 가져오고
        // 목차별로 가격가져오고
        // 목차별로 buyercrew 날짜, 가져오기
        List<Integer> projectIdList = accountService.getProjectIdListOfUser(userId);
        List<List<EachMonthProfitDTO>> eList = new ArrayList<List<EachMonthProfitDTO>>();
        for (Integer projectId : projectIdList) {
            eList.add(accountService.getEachMonthOfProfitForUser(userId, projectId));
        }

        return eList;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getAllProjectProfitForEachIndex")
    @ResponseStatus(value = HttpStatus.OK)
    public List<List<EachIndexProfitDTO>> getAllProjectProfitForEachIndex(@RequestHeader(value = "token") String token,
            @RequestBody UserIdDTO userIdDTO) {
        int userId = jwtUtil.getUserId(token);
        List<Integer> projectIdList = accountService.getProjectIdListOfUser(userId);
        List<List<EachIndexProfitDTO>> eList = new ArrayList<List<EachIndexProfitDTO>>();
        for (Integer projectId : projectIdList) {
            // eList.add();
            eList.add(accountService.getEachIndexTitleBuyerNumAllProfitMyProfit(userId, projectId));

        }

        return eList;
    }

}
