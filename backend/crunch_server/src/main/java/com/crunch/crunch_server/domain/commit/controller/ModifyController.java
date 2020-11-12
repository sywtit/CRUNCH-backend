package com.crunch.crunch_server.domain.commit.controller;

import com.crunch.crunch_server.diff.DiffProvider;
import com.crunch.crunch_server.domain.commit.dto.DiffDTO;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.tags.Param;

public class ModifyController {
    
    ///project/{projectId}/modify/basicTool/{IndexId}
    //get
    //post

    private DiffDTO diffDTO;
    private DiffProvider diffProvider;


    @CrossOrigin(origins="*")
    @PostMapping("/difftest/requestDiff")
    @ResponseStatus(value=HttpStatus.OK)
    public String showRecentPost(@RequestBody DiffDTO diffDTO) throws Exception 
    {
        System.out.println(diffProvider.getDiffStr(diffDTO.getBefore(), diffDTO.getAfter(),"Diff"));
        return diffProvider.getDiffStr(diffDTO.getBefore(), diffDTO.getAfter(), "Diff");
    }

}
