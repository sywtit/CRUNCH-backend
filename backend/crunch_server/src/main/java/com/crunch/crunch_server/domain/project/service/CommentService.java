package com.crunch.crunch_server.domain.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crunch.crunch_server.domain.project.dto.CommentDTO;
import com.crunch.crunch_server.domain.project.dto.CommentListReturnDTO;
import com.crunch.crunch_server.domain.project.dto.ProjectIndexUserDTO;
import com.crunch.crunch_server.domain.project.entity.Comment;
import com.crunch.crunch_server.domain.project.repository.CommentRepository;
import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.respository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

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

        LocalDate currentDate = LocalDate.now();

        comment.setTime(currentDate.toString());

        commentRepository.save(comment);
    }

    public List<CommentListReturnDTO> getCommentList(int userId, ProjectIndexUserDTO pDto) {
        List<Comment> comments = commentRepository.findByPostindexIdAndProjectId(pDto.getPostIndex(),
                pDto.getProjectId());
        List<CommentListReturnDTO> cDtos = new ArrayList<CommentListReturnDTO>();
        for (Comment comment : comments) {
            CommentListReturnDTO cDto = new CommentListReturnDTO();
            cDto.setIndexId(comment.getPostindexId());
            cDto.setProjectId(comment.getProjectId());
            cDto.setUserId(comment.getUserId());
            User user = userRepository.findById(comment.getUserId());
            cDto.setNickname(user.getNickname());
            cDto.setTime(comment.getTime());
            cDto.setText(comment.getText());

            cDtos.add(cDto);
        }

        return cDtos;
    }

}
