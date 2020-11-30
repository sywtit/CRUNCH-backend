package com.crunch.crunch_server.domain.user.service;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.crunch.crunch_server.domain.user.dto.UserDTO;
import com.crunch.crunch_server.domain.user.dto.UserInfoDTO;
import com.crunch.crunch_server.domain.user.dto.UserPointDTO;
import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.entity.Project;
import com.crunch.crunch_server.domain.project.repository.ProjectRepository;
import com.crunch.crunch_server.domain.user.dto.AccountDTO;
import com.crunch.crunch_server.domain.user.dto.ChargePointDTO;
import com.crunch.crunch_server.domain.user.dto.SessionRequestDTO;
import com.crunch.crunch_server.domain.user.entity.Account;
import com.crunch.crunch_server.domain.user.entity.User;
// import com.crunch.crunch_server.domain.user.mapper.AccountMapper;
import com.crunch.crunch_server.domain.user.mapper.UserChargePointMapper;
import com.crunch.crunch_server.domain.user.mapper.UserInfoMapper;
import com.crunch.crunch_server.domain.user.mapper.UserMapper;
import com.crunch.crunch_server.domain.user.mapper.UserPointMapper;
import com.crunch.crunch_server.domain.user.respository.AccountRepository;
import com.crunch.crunch_server.domain.user.respository.UserRepository;
import com.crunch.crunch_server.util.EncryptionUtil;
import com.crunch.crunch_server.util.JwtUtil;

@Service
public class AccountService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectRepository postIndexRepository;

    public List<AccountDTO> getAllAccountsById(int userId) {
        System.out.println(userId);
        System.out.println("=============================");
        List<Account> accounts = repository.findByUserId(userId);
        System.out.println(accounts.size());
        List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
        for (int i = 0; i < accounts.size(); i++) {
            // ApplyingWriterDTO apply = new ApplyingWriterDTO();
            // apply.setComment(applyingWriterList.get(i).getComment());
            // apply.setUserId(applyingWriterList.get(i).getWriterCrewIdentity().getUserId());
            // User user = userRepository.findById(apply.getUserId());
            // apply.setNickname(user.getNickname());
            // apply.setProjectId(applyingWriterList.get(i).getWriterCrewIdentity().getProjectId());
            // applyingWriterDTOs.add(i, apply);
            // System.out.println("heeeeeeellllo");
            AccountDTO oneaccount = new AccountDTO();
            oneaccount.setAccountHolder(accounts.get(i).getAccountHolder());
            oneaccount.setAccount_num(accounts.get(i).getAccount_num());
            oneaccount.setBank(accounts.get(i).getBank());
            accountDTOs.add(i, oneaccount);
        }
        return accountDTOs;
    }

    public void addnewAccount(AccountDTO accountDTO, int userId) {
        Account account = new Account();
        account.setUserId(userId);
        account.setAccountHolder(accountDTO.getAccountHolder());
        account.setAccount_num(accountDTO.getAccount_num());
        account.setBank(accountDTO.getBank());
        repository.save(account);

    }

    public void getAllProfit(int userId) {
        List<Project> projectList = projectRepository.findByUserId(userId);
        List<Integer> projectIdList = new ArrayList<Integer>();
        for (int i = 0; i < projectList.size(); i++) {
            projectIdList.add(i, projectList.get(i).getId());
        }

        List<PostIndex> postIndexs = postIndexRepository.findById(id);

    }
}
