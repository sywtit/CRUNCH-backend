package com.crunch.crunch_server.domain.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyWritingDTO {
    private String title;
    private String introduction;
    private int projectId;
    private List<String> writerNicknameList;
    // private String genre;
    private List<String> tagList;

}