package com.crunch.crunch_server.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPointDTO {
    private String identity;
    private int point;
    
    public void setPoint(int point)
    {
        this.point = point;
    }
}
