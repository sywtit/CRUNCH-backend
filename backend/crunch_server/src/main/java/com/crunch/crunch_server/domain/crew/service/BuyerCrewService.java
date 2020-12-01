package com.crunch.crunch_server.domain.crew.service;

import com.crunch.crunch_server.domain.crew.entity.BuyerCrew;
import com.crunch.crunch_server.domain.crew.entity.BuyerCrewIdentity;
// import com.crunch.crunch_server.domain.crew.entity.BuyerCrewIdentity;
import com.crunch.crunch_server.domain.crew.repository.BuyerCrewRepository;
import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.entity.Project;
import com.crunch.crunch_server.domain.project.repository.PostIndexRepository;
import com.crunch.crunch_server.domain.project.repository.ProjectRepository;
import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.respository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BuyerCrewService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ProjectRepository projectRepository;

        @Autowired
        private PostIndexRepository postIndexRepository;

        @Autowired
        private BuyerCrewRepository buyerCrewRepository;

        public void addBuyerCrew(int postIndex, int projectId, int userId) {

                // TmpBuyerCrew tmpBuyerCrew = new TmpBuyerCrew();
                // tmpBuyerCrew.setPostindexId(postIndex);
                // tmpBuyerCrew.setProjectId(projectId);
                // tmpBuyerCrew.setUserId(userId);
                // System.out.println("----------------3---------------");
                // buyerCrewRepository.save(tmpBuyerCrew);
                // System.out.println("----------------4---------------");
                BuyerCrew buyerCrew = new BuyerCrew();
                BuyerCrewIdentity buyerCrewIdentity = new BuyerCrewIdentity();
                buyerCrewIdentity.setPostindexId(postIndex);
                buyerCrewIdentity.setProjectId(projectId);
                buyerCrewIdentity.setUserId(userId);
                buyerCrew.setBuyerCrewIdentity(buyerCrewIdentity);
                buyerCrewRepository.save(buyerCrew);

        }

}
