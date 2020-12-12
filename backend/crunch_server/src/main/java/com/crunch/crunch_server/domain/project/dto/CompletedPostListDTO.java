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
public class CompletedPostListDTO {
    private String title;
    private List<String> writerList;
    private int LikeNum;

    
}
