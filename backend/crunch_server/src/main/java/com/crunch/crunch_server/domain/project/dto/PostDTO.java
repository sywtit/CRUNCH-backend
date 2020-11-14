package com.crunch.crunch_server.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int id;
    private int index_id;
    private int project_id;
    private String complete_post;
    
    
}
