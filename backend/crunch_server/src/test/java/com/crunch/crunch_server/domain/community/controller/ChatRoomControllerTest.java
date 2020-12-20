package com.crunch.crunch_server.domain.community.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.webservices.client.AutoConfigureWebServiceClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.JsonPath;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.crunch.crunch_server.domain.community.dto.FirstCommunityBlobDTO;
import com.crunch.crunch_server.domain.community.entity.Community;
import com.crunch.crunch_server.domain.community.repository.ChatRepository;
import com.crunch.crunch_server.domain.community.repository.ChatRoomRepository;
import com.crunch.crunch_server.domain.community.service.ChatRoomService;

// @WebMvcTest(controllers = ChatRoomController.class)
// @Import(ChatRoomController.class)
@RunWith(SpringRunner.class) 
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ChatRoomControllerTest {
    
     
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private WebApplicationContext wac;

    // @MockBean
    // private ChatRoomService chatRoomService;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
    
    }

    
    @Test
    public void makeChatCommunity() throws Exception{

        //setup fixture
        int projectId = 244;
        int indexId = 4;

        mvc.perform(
            MockMvcRequestBuilders.get("/api/project/244/index/4/makeChatRoom"))
            .andExpect(status().isOk());
        
            //check if server save new community
            //verify Outcome
            Community actualCommunity = chatRoomRepository.findByProjectIdAndPostindexId(projectId, indexId);
            Community expectedCommunity = new Community();
            expectedCommunity.setProjectId(projectId);
            expectedCommunity.setPostindexId(indexId);

            assertCommunityEquals(actualCommunity, expectedCommunity);
            
            
    }

    private void assertCommunityEquals(Community actualCommunity, Community expectedCommunity) {

        assertNotNull("community is null", actualCommunity);
        assertTrue(actualCommunity.getProjectId() == expectedCommunity.getProjectId());
        assertTrue(actualCommunity.getPostindexId() == expectedCommunity.getPostindexId());
    }


}
