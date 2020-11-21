package com.crunch.crunch_server.domain.crew.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.crunch.crunch_server.domain.crew.dto.BuyerCrewDTO;
import com.crunch.crunch_server.domain.crew.entity.BuyerCrew;
import com.crunch.crunch_server.domain.crew.entity.BuyerCrewEntity;
import com.crunch.crunch_server.domain.crew.entity.TmpBuyerCrew;
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

                TmpBuyerCrew tmpBuyerCrew = new TmpBuyerCrew();
                tmpBuyerCrew.setPostindexId(postIndex);
                tmpBuyerCrew.setProjectId(projectId);
                tmpBuyerCrew.setUserId(userId);
                buyerCrewRepository.save(tmpBuyerCrew);
                // BuyerCrew buyerCrew = new BuyerCrew();

                // System.out.println("----------------3---------------");
                // User user = userRepository.findById(userId);

                // System.out.println("----------------4---------------");
                // Project project;

                // System.out.println("----------------5---------------");
                // project = projectRepository.findById(projectId);

                // System.out.println("----------------6---------------");
                // PostIndex postIndexEntity =
                // postIndexRepository.findByIdAndProjectId(postIndex, project.getId());
                // System.out.println("----------------7---------------");
                // buyerCrew.setPostIndex(postIndexEntity);
                // System.out.println("----------------8---------------");

                // buyerCrew.setUser(user);
                // System.out.println(buyerCrew.getUser().getId());

                // System.out.println("----------------9---------------");
                // buyerCrewRepository.save(buyerCrew);
                // // EntityManager.persist(buyerCrew);
                // System.out.println("----------------10---------------");

        }

}
