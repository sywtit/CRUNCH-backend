package com.crunch.crunch_server.authentication;

import java.lang.annotation.ElementType;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasAutority('ROLE_MEMBER')")
public interface IsMemeber {
    
}
