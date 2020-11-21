package com.crunch.crunch_server.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStartDTO {
    private String title;
    private String introduction;
    private String image;
    private int mwn;
    private String target_d_day;
    private int target_funding_money;
}
