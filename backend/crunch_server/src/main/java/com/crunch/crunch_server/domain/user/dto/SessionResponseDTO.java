package com.crunch.crunch_server.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDTO {
    private String accessToken;
}
