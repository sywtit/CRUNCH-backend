package com.crunch.crunch_server.domain.user.service;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.crunch.crunch_server.domain.user.dto.UserDTO;
import com.crunch.crunch_server.domain.user.dto.UserInfoDTO;
import com.crunch.crunch_server.domain.user.dto.UserPointDTO;
import com.crunch.crunch_server.domain.crew.entity.BuyerCrew;
import com.crunch.crunch_server.domain.crew.entity.WritersCrew;
import com.crunch.crunch_server.domain.crew.repository.BuyerCrewRepository;
import com.crunch.crunch_server.domain.crew.repository.WriterCrewRepository;
import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.entity.Project;
import com.crunch.crunch_server.domain.project.repository.PostIndexRepository;
import com.crunch.crunch_server.domain.project.repository.ProjectRepository;
import com.crunch.crunch_server.domain.user.dto.AccountDTO;
import com.crunch.crunch_server.domain.user.dto.ChargePointDTO;
import com.crunch.crunch_server.domain.user.dto.EachIndexProfitDTO;
import com.crunch.crunch_server.domain.user.dto.EachMonthProfitDTO;
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
    private PostIndexRepository postIndexRepository;

    @Autowired
    private WriterCrewRepository writerCrewRepository;

    public List<AccountDTO> getAllAccountsById(int userId) {
        System.out.println(userId);
        System.out.println("=============================");
        List<Account> accounts = repository.findByUserId(userId);
        System.out.println(accounts.size());
        List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
        for (int i = 0; i < accounts.size(); i++) {
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

    // user id가 속한 프로젝트 리스트 별 postindex entity 가져오기
    public List<List<PostIndex>> getAllPostIndexLists(int userId) {

        List<Integer> projectIdList = getProjectIdListOfUser(userId);

        List<List<PostIndex>> pList = new ArrayList<List<PostIndex>>();
        for (int i = 0; i < projectIdList.size(); i++) {
            List<PostIndex> postIndexs = postIndexRepository.findByProjectId(projectIdList.get(i).intValue());
            pList.add(postIndexs);

        }

        return pList;

    }

    // userid 가 속한 프로젝트id 리스트 가져오기
    public List<Integer> getProjectIdListOfUser(int userId) {
        List<Integer> projectIdList = new ArrayList<Integer>();
        // writerCrewRepository.findByWriterCrewIdentityUserId()
        List<WritersCrew> writersCrews = getWritersCrewListOfUser(userId);
        for (int i = 0; i < writersCrews.size(); i++) {
            projectIdList.add(i, writersCrews.get(i).getWriterCrewIdentity().getProjectId());
        }
        return projectIdList;

    }

    public List<WritersCrew> getWritersCrewListOfUser(int userId) {
        return writerCrewRepository.findByWriterCrewIdentityUserId(userId);
    }

    public int getMoneyPercentForEachProject(int userId, int projectId) {
        WritersCrew writersCrew = writerCrewRepository
                .findByWriterCrewIdentityUserIdAndWriterCrewIdentityProjectId(userId, projectId);
        return writersCrew.getMoney_percent();
    }

    public int getFeeOfProjectIdAndPostIndexId(int projectId, int postindexId) {
        PostIndex postindex = postIndexRepository.findByIdAndProjectId(postindexId, projectId);
        return postindex.getFee();
    }

    public List<EachMonthProfitDTO> getEachMonthOfProfitForUser(int userId, int projectId) {
        List<EachMonthProfitDTO> eDtos = new ArrayList<EachMonthProfitDTO>();
        for (int i = 1; i < 13; i++) {
            LocalDate start = LocalDate.of(2020, i, 1);
            LocalDate end = LocalDate.of(2020, i + 1, 1);
            List<BuyerCrew> buyerCrews = BuyerCrewRepository.findByPurchaseDateBetween(start, end);
            int allProfit = 0;
            for (BuyerCrew oneCrew : buyerCrews) {
                allProfit += getFeeOfProjectIdAndPostIndexId(oneCrew.getBuyerCrewIdentity().getProjectId(),
                        oneCrew.getBuyerCrewIdentity().getPostindexId());
            }

            int myProfit = allProfit * getMoneyPercentForEachProject(userId, projectId);
            EachMonthProfitDTO eDto = new EachMonthProfitDTO();
            eDto.setMonth(i);
            eDto.setMyProfit(myProfit);
            eDtos.add(eDto);

        }
        return eDtos;
    }

    public List<EachIndexProfitDTO> getEachIndexTitleBuyerNumAllProfitMyProfit(int userId, Integer projectId) {
        List<EachIndexProfitDTO> eDtos = new ArrayList<EachIndexProfitDTO>();
        List<PostIndex> postIndexs = postIndexRepository.findByProjectId(projectId);
        for (PostIndex pIndex : postIndexs) {
            EachIndexProfitDTO eDto = new EachIndexProfitDTO();
            eDto.setProjectId(projectId);
            int postidx = pIndex.getId();
            eDto.setPostIndexId(postidx);
            eDto.setPostIndexTitle(pIndex.getTitle());

            // 이 목차의 all profit
            List<BuyerCrew> buyerCrews = BuyerCrewRepository
                    .findByBuyerCrewIdentityPostindexIdAndBuyerCrewIdentityProjectId(postidx, projectId);
            int fee = getFeeOfProjectIdAndPostIndexId(postidx, projectId);
            eDto.setBuyerCrewNum(buyerCrews.size());
            eDto.setAllProfit(fee * eDto.getBuyerCrewNum());
            eDto.setMyProfit(eDto.getAllProfit() * getMoneyPercentForEachProject(userId, projectId));

            eDtos.add(eDto);

        }

        return eDtos;

    }
}
