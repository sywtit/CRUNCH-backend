package com.crunch.crunch_server.domain.commit.controller;

import com.crunch.crunch_server.diff.DiffProvider;
import com.crunch.crunch_server.domain.commit.dto.DiffDTO;
import com.crunch.crunch_server.domain.commit.dto.ModifyDTO;
import com.crunch.crunch_server.domain.commit.dto.TestDTO;
import com.crunch.crunch_server.domain.commit.service.ModifyService;
import com.crunch.crunch_server.domain.project.service.PostService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.tags.Param;

@RestController
@RequestMapping("/api/project")
public class ModifyController {
    
    
    @Autowired
    private ModifyService modifyService;

    @Autowired
    private PostService postService;

    @CrossOrigin(origins="*")
    @PostMapping(value="/{projectId}/modify/basicTool/{indexId}",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(value=HttpStatus.OK)
    void saveDiffResult(
        @RequestHeader(value="token") String token ,
        @PathVariable int projectId, @PathVariable int indexId,
        @RequestBody ModifyDTO modifyDTO) throws Exception
    {

        modifyService.saveNewCommit(token, projectId, indexId, modifyDTO);

        int postId = postService.getPostID(projectId, indexId);
        modifyService.setModifiedComplete(postId);

    }
    
    @CrossOrigin(origins="*")
    @PostMapping(value="/file",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(value=HttpStatus.OK)
    void saveFiles(
        @RequestParam("files") MultipartFile[] files) throws Exception
    {

        modifyService.setWholeFiles(files);

    }

    //for postman test
    @CrossOrigin(origins="*")
    @PostMapping(value = "/test",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(value=HttpStatus.OK)
    void checkFile(
        @RequestParam("file") MultipartFile[] files) throws Exception
    {
        System.out.println(files.length);

    }



    @CrossOrigin(origins="*")
    @PostMapping("/{projectId}/pressModifyButton/{indexId}")
    @ResponseStatus(value=HttpStatus.OK)
    public String checkAccessBefore(
        @RequestHeader(value="token") String token,
        @PathVariable int projectId, @PathVariable int indexId,
        @RequestBody Map<String, Object> requestString) throws JsonParseException
    {
        int postId = postService.getPostID(projectId, indexId);

        return modifyService.checkModifying(token, postId);

    }

    @CrossOrigin(origins="*")
    @PostMapping("/{projectId}/pressModifyCancelButton/{indexId}")
    @ResponseStatus(value=HttpStatus.OK)
    public void checkAccessCancel(
        @RequestHeader(value="token") String token,
        @PathVariable int projectId, @PathVariable int indexId,
        @RequestBody Map<String, Object> requestString) throws JsonParseException
    {
        int postId = postService.getPostID(projectId, indexId);

        modifyService.cancelModifying(token, postId);

    }


}
