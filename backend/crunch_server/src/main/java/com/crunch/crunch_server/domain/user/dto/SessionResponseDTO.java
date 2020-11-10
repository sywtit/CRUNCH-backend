package com.crunch.crunch_server.domain.user.dto;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.crunch.crunch_server.domain.user.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDTO {
    private String accessToken;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Embedded
    private UserInfoDTO userInfoDTO;
    

    public String getRoleKey() 
    {
        return this.role.getKey();
    }

}
