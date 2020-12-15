package com.crunch.crunch_server.domain.project.controller;

import java.util.List;

import com.crunch.crunch_server.domain.project.dto.CommentDTO;
import com.crunch.crunch_server.domain.project.dto.CommentListReturnDTO;
import com.crunch.crunch_server.domain.project.entity.Comment;
import com.crunch.crunch_server.domain.project.dto.ProjectIndexUserDTO;
import com.crunch.crunch_server.domain.project.service.CommentService;
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
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    private JwtUtil jwtUtil;

    // @CrossOrigin(origins = "*")
    // @PostMapping("/getcomment")
    // @ResponseStatus(value = HttpStatus.OK)
    // public List<Comment> getcomment(@RequestHeader(value = "token") String token,
    // @RequestBody ProjectIndexUserDTO projectIndexUserDTO) {

    // // int userId = jwtUtil.getUserId(token);
    // System.out.println("hello");
    // return commentService.getIndexComment(projectIndexUserDTO);

    // }

    // @CrossOrigin(origins = "*")
    // @PostMapping("/addcomment")
    // @ResponseStatus(value = HttpStatus.OK)
    // public int addComment(@RequestHeader(value = "token") String token,
    // @RequestBody CommentDTO commentDTO) {
    // int userId = jwtUtil.getUserId(token);
    // System.out.println("############")
    // System.out.println(commentDTO.getText());
    // commentService.addMyComment(userId, commentDTO);
    // return 100;
    // }
    @CrossOrigin(origins = "*")
    @PostMapping("/getCommentList")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CommentListReturnDTO> getCommentListOfProjectIndexId(@RequestHeader(value = "token") String token,
            @RequestBody ProjectIndexUserDTO pDto) {

        int userId = jwtUtil.getUserId(token);
        System.out.println("############");
        return commentService.getCommentList(userId, pDto);

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/submitComment")
    @ResponseStatus(value = HttpStatus.OK)
    public void submitComment(@RequestHeader(value = "token") String token, @RequestBody CommentDTO commentDTO) {

        int userId = jwtUtil.getUserId(token);
        System.out.println("############");
        commentService.addMyComment(userId, commentDTO);

    }

}
