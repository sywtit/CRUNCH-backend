package com.crunch.crunch_server.authentication.dto;

import java.security.Principal;

public final class User implements Principal{
    private final String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public String getName() {
        return username;
    }
}
