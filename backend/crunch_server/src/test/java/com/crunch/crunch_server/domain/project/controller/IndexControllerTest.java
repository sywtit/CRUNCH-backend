package com.crunch.crunch_server.domain.project.controller;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.crunch.crunch_server.domain.project.dto.IndexEditDTO;
import com.crunch.crunch_server.domain.project.entity.Posts;
import com.crunch.crunch_server.domain.project.repository.PostRepository;
import com.crunch.crunch_server.domain.user.dto.SessionRequestDTO;
import com.crunch.crunch_server.domain.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class) 
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class IndexControllerTest {

    
    @Autowired
    private MockMvc mvc;
    ObjectMapper mapper;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;


    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
        mapper = new ObjectMapper();
    
    }

    
    @Test
    public void indexEditToMakePost() throws Exception{

        //setup fixture
        int projectId = 249;
        int indexId = 3;
        IndexEditDTO dto = new IndexEditDTO(indexId, projectId, "test");
        SessionRequestDTO session = new SessionRequestDTO();
        session.setIdentity("hello");
        session.setPassword("1234");
        String token = userService.createToken(session);

        mvc.perform(
            MockMvcRequestBuilders.post("/api/project/indexedit")
            .contentType(MediaType.ALL)
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(toJsonString(dto))
            .header("token", token)
            .with(csrf()) 
            )
            .andExpect(status().isOk());
        
            //verify Outcome
            Posts actualPosts = postRepository.findByProjectIdAndIndexId(projectId, indexId);
            Posts expectedPosts = new Posts();
            expectedPosts.setIndex_id(3);
            expectedPosts.setProject_id(249);

            assertPostsEquals(actualPosts, expectedPosts);
            
            //cleaning up fixture
            deleteObject(dto);
            deleteObject(session);
            
    }

    private void assertPostsEquals(Posts actualPosts, Posts expectedPosts) {
        assertNotNull("post is null", actualPosts);        
        assertEquals(actualPosts.getProject_id(), expectedPosts.getProject_id());
        assertEquals(actualPosts.getIndex_id(), expectedPosts.getIndex_id());
        noWriterModifyingNow(actualPosts);
        noWriterIdModifyingNow(actualPosts);
    
    }

    private void deleteObject(Object ob) {
        ob = null;
    }
    
    private void noWriterIdModifyingNow(Posts actualPosts) {
        assertTrue(actualPosts.getModifyingUserId() == -1);
    }


    private void noWriterModifyingNow(Posts actualPosts) {
        assertTrue(actualPosts.getModifying() == 0);
    }

    private String toJsonString(IndexEditDTO dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }
    

    
}
