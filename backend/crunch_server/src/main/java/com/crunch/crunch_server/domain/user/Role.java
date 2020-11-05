package com.crunch.crunch_server.domain.user;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    GUEST("ROLE_GUEST"),
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    private final String key;

}
