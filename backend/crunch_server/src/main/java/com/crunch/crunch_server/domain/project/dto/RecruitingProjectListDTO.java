package com.crunch.crunch_server.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitingProjectListDTO {
    private String title;
    private String mainWriter;
    private int recruitingNum;
    private int applyingNum;
    private String targetDDay;
}
