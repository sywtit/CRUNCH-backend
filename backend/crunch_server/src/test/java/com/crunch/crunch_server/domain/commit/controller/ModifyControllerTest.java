package com.crunch.crunch_server.domain.commit.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.hamcrest.Matchers.*;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.crunch.crunch_server.domain.commit.dto.ModifyDTO;
import com.crunch.crunch_server.domain.commit.dto.ModifyTestDTO;
import com.crunch.crunch_server.domain.commit.entity.Commits;
import com.crunch.crunch_server.domain.commit.repository.ModifyCommitRepoistory;
import com.crunch.crunch_server.domain.project.entity.Posts;
import com.crunch.crunch_server.domain.project.repository.PostRepository;
import com.crunch.crunch_server.domain.project.service.PostService;
import com.crunch.crunch_server.domain.user.dto.SessionRequestDTO;
import com.crunch.crunch_server.domain.user.service.UserService;
import com.crunch.crunch_server.util.JwtUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ModifyControllerTest {

    @Autowired
    private MockMvc mvc;
    ObjectMapper mapper;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private ModifyCommitRepoistory modifyCommitRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private SessionRequestDTO session;
    private ModifyDTO modifyDTO;

    private String now = "2020-12-22 12:13";
    private LocalDateTime defaultCurrentTime1;
    
    @Before
    public void setup() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print()).build();

        session = new SessionRequestDTO();
        session.setIdentity("hello");
        session.setPassword("1234");

        firstModifyDTO();

        mapper = new ObjectMapper();

    }

    private void firstModifyDTO() {
        modifyDTO = new ModifyDTO();
        modifyDTO.setAfter("<p>first paragraph</p>\n");
        modifyDTO.setCommit_comment("first commit"); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        defaultCurrentTime1 = LocalDateTime.parse(now, formatter);
        modifyDTO.setTime(defaultCurrentTime1);
    }

    @Test
    public void whenPressModifyButton() throws Exception {

        // setup fixture
        int projectId = 249;
        int indexId = 2;
        Map<String, Object> tmpnull = new HashMap<String, Object>();
        tmpnull.put("tmp", "nothing");

        String token = userService.createToken(session);

        mvc.perform(MockMvcRequestBuilders.post("/api/project/249/pressModifyButton/2").contentType(MediaType.ALL)
                .accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonStringWithMap(tmpnull))
                .header("token", token)
                .with(csrf())
                ).andExpect(status().isOk());

        // verify Outcome
        Posts actualPosts = postRepository.findByProjectIdAndIndexId(projectId, indexId);
        int userId = jwtUtil.getUserId(token);
        assertPostsModifyingStatusUpdate(actualPosts, userId);

        // cleaning up fixture
        deleteObject(tmpnull);
    }

    @Test
    public void whenPressModifyCancelButton() throws Exception {

        // setup fixture
        int projectId = 249;
        int indexId = 2;
        Map<String, Object> tmpnull = new HashMap<String, Object>();
        tmpnull.put("tmp", "nothing");

        System.out.println("for just check");
        String token = userService.createToken(session);

        mvc.perform(MockMvcRequestBuilders.post("/api/project/249/pressModifyCancelButton/2").contentType(MediaType.ALL)
                .accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonStringWithMap(tmpnull))
                .header("token", token)
                .with(csrf())
                ).andExpect(status().isOk());

        // verify Outcome
        Posts actualPosts = postRepository.findByProjectIdAndIndexId(projectId, indexId);
        assertPostsModifyingStatusInitialize(actualPosts);

        // cleaning up fixture
        deleteObject(tmpnull);
    }

    
    @Test
    public void modifyFunctionCheck() throws Exception {

        // setup fixture
        int projectId = 249;
        int indexId = 2;

        System.out.println("for just check");
        String token = userService.createToken(session);
      
        ModifyTestDTO modifyTestDTO1 = new ModifyTestDTO("<p>first paragraph</p>", now, "first commit");

        mvc.perform(MockMvcRequestBuilders.post("/api/project/249/modify/basicTool/2")
                .accept(MediaType.MULTIPART_FORM_DATA_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonModifyString(modifyTestDTO1))
                .header("token", token)
                .with(csrf())
                ).andExpect(status().isOk());

        // verify Outcome
        int postId = postService.getPostID(projectId, indexId);
        Commits actualCommits = modifyCommitRepository.findByPostIdAndGetNew(postId);
        assertCommitsUpdateProperly(actualCommits);

        // cleaning up fixture
        // nothing to clean up
    
    }


    private void assertCommitsUpdateProperly(Commits actualCommits) {
        assertNotNull("commit is null", actualCommits);
        assertEquals(actualCommits.getCommit_comment(), modifyDTO.getCommit_comment());
        assertEquals(actualCommits.getPost(), modifyDTO.getAfter());
        assertEquals(actualCommits.getTime(), modifyDTO.getTime());
    }

    private void assertPostsModifyingStatusInitialize(Posts actualPosts) {
        assertNotNull("post is null", actualPosts);
        noWriterModifyingNow(actualPosts);
        noWriterIdModifyingNow(actualPosts);
    }

    private void assertPostsModifyingStatusUpdate(Posts actualPosts, int userId) {

        assertNotNull("post is null", actualPosts);
        SomeWriterModifyingNow(actualPosts);
        SomeWriterIdModifyingNow(actualPosts, userId);
    }

    private void SomeWriterIdModifyingNow(Posts actualPosts, int userId) {
        assertTrue(actualPosts.getModifyingUserId() == userId);
    }

    private void SomeWriterModifyingNow(Posts actualPosts) {
        assertTrue(actualPosts.getModifying() == 1);
    }

    private void noWriterIdModifyingNow(Posts actualPosts) {
        assertTrue(actualPosts.getModifyingUserId() == -1);
    }


    private void noWriterModifyingNow(Posts actualPosts) {
        assertTrue(actualPosts.getModifying() == 0);
    }

    private String toJsonStringWithMap(Map<String, Object> dto) throws JsonProcessingException, JSONException {
        
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : dto.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }
        
        return jsonObject.toString();
    }

    private String toJsonModifyString(ModifyTestDTO dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }

    private void deleteObject(Object ob) {
        ob = null;
    }


}
