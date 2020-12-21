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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.crunch.crunch_server.domain.project.entity.Posts;
import com.crunch.crunch_server.domain.project.repository.PostRepository;
import com.crunch.crunch_server.domain.user.dto.SessionRequestDTO;
import com.crunch.crunch_server.domain.user.service.UserService;
import com.crunch.crunch_server.util.JwtUtil;
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
    private JwtUtil jwtUtil;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print()).build();
    }

    @Test
    public void whenPressModifyButton() throws Exception {

        // setup fixture
        int projectId = 249;
        int indexId = 2;
        Map<String, Object> tmpnull = new HashMap<String, Object>();
        tmpnull.put("tmp", "nothing");

        System.out.println("for just check");
        SessionRequestDTO session = new SessionRequestDTO();
        session.setIdentity("hello");
        session.setPassword("1234");
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
        deleteObject(session);
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

    private String toJsonStringWithMap(Map<String, Object> dto) throws JsonProcessingException, JSONException {
        
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : dto.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }
        
        return jsonObject.toString();
    }

    private void deleteObject(Object ob) {
        ob = null;
    }


    // @CrossOrigin(origins = "*")
    // @PostMapping("/{projectId}/pressModifyCancelButton/{indexId}")
    // @ResponseStatus(value = HttpStatus.OK)
    // public void checkAccessCancel(@RequestHeader(value = "token") String token, @PathVariable int projectId,
    //         @PathVariable int indexId, @RequestBody Map<String, Object> requestString) throws JsonParseException {
    //     int postId = postService.getPostID(projectId, indexId);

    //     modifyService.cancelModifying(token, postId);

    // }
}
