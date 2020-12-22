package com.crunch.crunch_server.domain.commit.controller;

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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.crunch.crunch_server.domain.commit.dto.ModifyDTO;
import com.crunch.crunch_server.domain.commit.dto.ModifyTestDTO;
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
public class BlobControllerTest {

    @Autowired
    private MockMvc mvc;
    ObjectMapper mapper;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private SessionRequestDTO session;
    private SessionRequestDTO session1;
    private ModifyDTO modifyDTO;
    private ModifyDTO modifyDTO1;

    private String now = "2020-12-22 12:13";
    private LocalDateTime defaultCurrentTime1;
    private LocalDateTime defaultCurrentTime2;
    
    @Before
    public void setup() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print()).build();

        session = new SessionRequestDTO();
        session.setIdentity("hello");
        session.setPassword("1234");

        session1 = new SessionRequestDTO();
        session1.setIdentity("writer1");
        session1.setPassword("1234");

        firstModifyDTO();
        secondModifyDTO();

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

    private void secondModifyDTO() {
        modifyDTO1 = new ModifyDTO();
        modifyDTO1.setAfter("<p>first paragraph</p>\n<p>second paragraph</p>\n");
        modifyDTO1.setCommit_comment("second commit"); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        defaultCurrentTime2 = LocalDateTime.parse(now, formatter);
        modifyDTO1.setTime(defaultCurrentTime2);
    }



    
    @Test
    public void showFirstPost() throws Exception{

        //setup fixture
        int projectId = 249;

        mvc.perform(
            MockMvcRequestBuilders.get("/api/project/249/blob/basicTool/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]").doesNotExist())
            .andExpect(jsonPath("$[0].post").doesNotExist())
            .andExpect(jsonPath("$[0].postDetailList").doesNotExist());
            
    }

    @Test
    public void showRecentPost() throws Exception{

        //setup fixture
        int projectId = 249;
        int indexId = 3;
        String token = userService.createToken(session);
        String token1 = userService.createToken(session1);

        // //modify post first
        // ModifyTestDTO modifyTestDTO1 = new ModifyTestDTO("<p>first paragraph</p>", now, "first commit");
        // ModifyTestDTO modifyTestDTO2 = new ModifyTestDTO("<p>first paragraph</p><p>second paragraph</p>", now, "second commit");

        // ModifyControll(token, modifyTestDTO1);
        // ModifyControll(token, modifyTestDTO2);

        //than get basic blob 
        // verify Outcome
        mvc.perform(
            MockMvcRequestBuilders.get("/api/project/249/blob/basicTool/3"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.postDetailList[0].text").value("<p>first paragraph</p>"))
            .andExpect(jsonPath("$.postDetailList[1].text").value("<p>second paragraph</p>"))
            .andExpect(jsonPath("$.postDetailList[0].writerName").value("star"))
            .andExpect(jsonPath("$.postDetailList[1].writerName").value("yolo"));
            

        // cleaning up fixture
        // deleteObject(modifyTestDTO1);
        // deleteObject(modifyTestDTO2);
    }

    private void ModifyControll(String token, ModifyTestDTO modifyTestDTO1) throws Exception, JsonProcessingException {
        mvc.perform(MockMvcRequestBuilders.post("/api/project/249/modify/basicTool/3")
                .accept(MediaType.MULTIPART_FORM_DATA_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonModifyString(modifyTestDTO1))
                .header("token", token)
                .with(csrf())
                ).andExpect(status().isOk());
    }

    private String toJsonModifyString(ModifyTestDTO dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }

    private void deleteObject(Object ob) {
        ob = null;
    }

}
