package com.crunch.crunch_server.domain.crew.service;

import java.util.ArrayList;
import java.util.List;

import com.crunch.crunch_server.domain.crew.dto.ApplyingWriterDTO;
import com.crunch.crunch_server.domain.crew.dto.WriterCrewCheckDTO;
import com.crunch.crunch_server.domain.crew.entity.State;
import com.crunch.crunch_server.domain.crew.entity.WriterCrewIdentity;
import com.crunch.crunch_server.domain.crew.entity.WritersCrew;
import com.crunch.crunch_server.domain.crew.mapper.CheckMapper;
import com.crunch.crunch_server.domain.crew.repository.WriterCrewRepository;
import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.respository.UserRepository;
import com.crunch.crunch_server.domain.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriterCrewService {

    @Autowired
    private WriterCrewRepository writerRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    // public Boolean isWriter(int userId) {
    // WritersCrew writercrew =
    // writerRepository.findByWriterCrewIdentityUserId(userId);
    // WriterCrewCheckDTO checkcrew = CheckMapper.Instance.toDTO(writercrew);

    // if (hasWriterStatus(checkcrew))
    // return true;
    // else
    // return false;
    // }

    private boolean hasWriterStatus(WriterCrewCheckDTO checkcrew) {
        return checkcrew.getLimit_status() == 0 && checkcrew.getState().equals("selected");
    }

    public void addWriterApply(int userId, ApplyingWriterDTO applyingWriterDTO) {
        WritersCrew writersCrew = new WritersCrew();
        writersCrew.setComment(applyingWriterDTO.getComment());

        WriterCrewIdentity writerCrewIdentity = new WriterCrewIdentity();
        writerCrewIdentity.setProjectId(applyingWriterDTO.getProjectId());
        writerCrewIdentity.setUserId(userId);
        writersCrew.setWriterCrewIdentity(writerCrewIdentity);

        writerRepository.save(writersCrew);
        // TmpWriterCrew tmpWriterCrew = new TmpWriterCrew();
        // tmpWriterCrew.setComment(comment);
        // tmpWriterCrew.setUserId(userId);

    }

    public String getWriterName(int userId) {
        System.out.println(userRepository);

        user = userRepository.findById(userId);
        return user.getNickname();
    }

    public int getMainorApply(int userId, int id) {

        return writerRepository.findByWriterCrewIdentityUserIdAndWriterCrewIdentityProjectId(userId, id).getMainornot();
    }

    public void addMainWriter(int userId, int projectId) {
        WritersCrew writersCrew = new WritersCrew();
        writersCrew.setComment(null);
        writersCrew.setMainornot(1);
        WriterCrewIdentity writerCrewIdentity = new WriterCrewIdentity();
        writerCrewIdentity.setProjectId(projectId);
        writerCrewIdentity.setUserId(userId);
        writersCrew.setWriterCrewIdentity(writerCrewIdentity);

        writerRepository.save(writersCrew);

    }

    public List<ApplyingWriterDTO> getApplyingWriters(int project_id) {
        System.out.println("adfsdf");
        System.out.println(project_id);
        List<WritersCrew> applyingWriterList = writerRepository.findByWriterCrewIdentityProjectId(project_id);

        List<ApplyingWriterDTO> applyingWriterDTOs = new ArrayList<ApplyingWriterDTO>();
        for (int i = 0; i < applyingWriterList.size(); i++) {
            ApplyingWriterDTO apply = new ApplyingWriterDTO();
            if (applyingWriterList.get(i).getMainornot() != 1) {
                apply.setComment(applyingWriterList.get(i).getComment());
                apply.setUserId(applyingWriterList.get(i).getWriterCrewIdentity().getUserId());
                User user = userRepository.findById(apply.getUserId());
                apply.setNickname(user.getNickname());
                apply.setProjectId(applyingWriterList.get(i).getWriterCrewIdentity().getProjectId());
                applyingWriterDTOs.add(apply);
                // System.out.println("heeeeeeellllo");
            }

        }
        // System.out.println(applyingWriterDTOs.size());

        return applyingWriterDTOs;
    }

    public void adoptSelectedWriters(List<Integer> userIdList, int projectId) {
        // List<User> userList = new ArrayList<User>();
        // System.out.println(userIdList.getClass());
        for (int i = 0; i < userIdList.size(); i++) {
            System.out.println("-----*------*----*");
            System.out.println(userIdList.get(i));

            // User user = userRepository.findById(userIdList.get(i).intValue());
            // System.out.println(user.getIdentity());
            // WritersCrew writersCrew =
            // writerRepository.findByWriterCrewIdentityUserId(userIdList.get(i).intValue());
            WritersCrew writersCrew = writerRepository
                    .findByWriterCrewIdentityUserIdAndWriterCrewIdentityProjectId(userIdList.get(i), projectId);
            writersCrew.setState(State.selected);
            writerRepository.save(writersCrew);

        }
    }

}
