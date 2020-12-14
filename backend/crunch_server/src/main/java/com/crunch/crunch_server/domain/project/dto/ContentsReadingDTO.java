package com.crunch.crunch_server.domain.project.dto;

import java.util.List;

import com.crunch.crunch_server.domain.user.dto.UserIdNickDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsReadingDTO {
    private int projectId;
    private String title;

    private String subtitle;
    private List<UserIdNickDTO> writerNicknameList;
    // 프젝종료날짜
    private String complete_time;
    // 태그
    private List<String> tagList;
    // 별점
    private double starRate;
    // 내용
    private String completePost;

}
