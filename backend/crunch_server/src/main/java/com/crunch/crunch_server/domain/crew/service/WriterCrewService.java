package com.crunch.crunch_server.domain.crew.service;

import com.crunch.crunch_server.domain.crew.dto.WriterCrewCheckDTO;
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
    private UserService userService;
    
    public Boolean isWriter(int userId)
    {
        WritersCrew writercrew = writerRepository.findByWriterCrewIdentityUserId(userId);
        WriterCrewCheckDTO checkcrew = CheckMapper.Instance.toDTO(writercrew);

        if(hasWriterStatus(checkcrew)) return true;
        else return false;
    }

    private boolean hasWriterStatus(WriterCrewCheckDTO checkcrew) {
        return checkcrew.getLimit_status()==0 && checkcrew.getState().equals("selected");
    }

    public String getWriterName(int userId)
    {
        User user = userService.getUserById(userId);
        return user.getNickname();
    }
}
