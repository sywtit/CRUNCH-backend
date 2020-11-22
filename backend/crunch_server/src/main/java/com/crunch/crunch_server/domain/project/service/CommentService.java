package com.crunch.crunch_server.domain.project.service;

import java.util.List;

import com.crunch.crunch_server.domain.project.dto.CommentDTO;
import com.crunch.crunch_server.domain.project.dto.ProjectIndexUserDTO;
import com.crunch.crunch_server.domain.project.entity.Comment;
import com.crunch.crunch_server.domain.project.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getIndexComment(ProjectIndexUserDTO projectIndexUserDTO) {
        // Comment comment = new Comment();
        // comment.setPostindexId(projectIndexUserDTO.getPostIndex());
        // comment.setProjectId(projectIndexUserDTO.getProjectId());
        List<Comment> commentList = commentRepository.findByPostindexIdAndProjectId(projectIndexUserDTO.getPostIndex(),
                projectIndexUserDTO.getProjectId());
        // int last = commits.size() - 1;
        System.out.println(commentList);
        return commentList;
        // CommentDTO commentDTO = CommitMapper.Instance.toCommentDTO((Comment)
        // commentList.get(last));
        // return commitDTO;
    }

    public void addMyComment(int userId, CommentDTO commentDTO) {

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setPostindexId(commentDTO.getPostindexId());
        comment.setProjectId(commentDTO.getProjectId());
        comment.setText(commentDTO.getText());
        comment.setTime(commentDTO.getTime());

        commentRepository.save(comment);
    }

}
