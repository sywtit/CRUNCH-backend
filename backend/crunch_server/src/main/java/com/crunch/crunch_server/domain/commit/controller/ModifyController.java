package com.crunch.crunch_server.domain.commit.controller;

import com.crunch.crunch_server.diff.DiffProvider;
import com.crunch.crunch_server.domain.commit.dto.DiffDTO;
import com.crunch.crunch_server.domain.commit.dto.ModifyDTO;
import com.crunch.crunch_server.domain.commit.service.ModifyService;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.Param;

@RestController
@RequestMapping("/api/project")
public class ModifyController {
    
    
    @Autowired
    private ModifyService modifyService;

    // @CrossOrigin(origins="*")
    // @PostMapping("/api/difftest/requestDiff")
    // @ResponseStatus(value=HttpStatus.OK)
    // public String showRecentPost(@RequestBody DiffDTO diffDTO) throws Exception 
    // {
    //     System.out.println(DiffProvider.getDiffStr(diffDTO.getBefore(), diffDTO.getAfter(),"Diff"));
    //     return DiffProvider.getDiffStr(diffDTO.getBefore(), diffDTO.getAfter(), "Diff");
    // }

    @CrossOrigin(origins="*")
    @PostMapping("/{projectId}/modify/basicTool/{indexId}")
    @ResponseStatus(value=HttpStatus.OK)
    void saveDiffResult(
        @RequestHeader(value="token") String token ,
        @PathVariable int projectId, @PathVariable int indexId,
        @RequestBody ModifyDTO modifyDTO) throws Exception
    {
        System.out.println(modifyDTO.getTime());
        modifyService.saveNewCommit(token, projectId, indexId, modifyDTO);
    }

}
