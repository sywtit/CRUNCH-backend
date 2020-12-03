package com.crunch.crunch_server.domain.project.controller;

import java.util.List;

import com.crunch.crunch_server.domain.project.dto.CommentDTO;
import com.crunch.crunch_server.domain.project.dto.IndexEditDTO;
import com.crunch.crunch_server.domain.project.dto.IndexTitleDTO;
import com.crunch.crunch_server.domain.project.dto.ProjectIdDTO;
import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.repository.PostIndexRepository;
import com.crunch.crunch_server.domain.project.service.PostService;
import com.crunch.crunch_server.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PostService postService;

    @CrossOrigin(origins = "*")
    @PostMapping("/getindex")
    @ResponseStatus(value = HttpStatus.OK)
    public List<IndexTitleDTO> getIndex(@RequestHeader(value = "token") String token,
            @RequestBody ProjectIdDTO projectIdDTO) {

        int userId = jwtUtil.getUserId(token);
        System.out.println(projectIdDTO.getId());
        return postService.getIndexsOfProjectId(projectIdDTO.getId());

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/project/indexedit")
    @ResponseStatus(value = HttpStatus.OK)
    public int indexedit(@RequestHeader(value = "token") String token, @RequestBody IndexEditDTO indexEditDTO) {

        int userId = jwtUtil.getUserId(token);
        // postService.setPostIndexEdit(indexEditDTOs);
        System.out.println("before addlast");
        postService.addLastPostIndex(indexEditDTO);

        return 100;
    }
}
