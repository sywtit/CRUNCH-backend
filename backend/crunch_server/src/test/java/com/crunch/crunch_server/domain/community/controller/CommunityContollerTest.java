package com.crunch.crunch_server.domain.community.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.JsonPath;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print; 

import com.crunch.crunch_server.domain.community.dto.FirstCommunityBlobDTO;

@RunWith(SpringRunner.class) 
@WebMvcTest(controllers = CommunityContoller.class)
@Import(CommunityContoller.class)
@AutoConfigureMockMvc
public class CommunityContollerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    CommunityContoller communityController;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(communityController).build();
    
    }


    @Test
    public void checkGeneralCommunityBlobDTO() throws Exception {
 
        //just for annotation
        int projectTestId = 172;
        int indexTestId = 0;

        mvc.perform(get("/api/project/172/index/0/CommunityBlob")
        // .param("projectId", projectTestId)
        // .param("indexId", indexTestId) 
        )
                .andExpect(status().isOk())
                .andDo(print());


    //             .andExpect(jsonPath("$[*]", hasSize(2)))
    // .andExpect(jsonPath("$[0].id", is(1)))
    // .andExpect(jsonPath("$[0].quote", is("Hasta la vista, baby")))

               // content().string(hello)
                // .andExpect(model().attributeExists("result"))

                // .andExpect(model().attribute("result", "Hello World!@#"));
    }

}
