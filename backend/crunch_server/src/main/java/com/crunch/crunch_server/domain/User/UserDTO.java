package com.crunch.crunch_server.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String identity;

    @JsonIgnore
    private String password;

    private String name;
    private String nickname;
    private String gender;
    private String record;

}
