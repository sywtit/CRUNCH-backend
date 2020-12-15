package com.crunch.crunch_server.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListReturnDTO {
    private int projectId;
    private int indexId;
    private String nickname;
    private int userId;
    private String text;
    private String time;

}
