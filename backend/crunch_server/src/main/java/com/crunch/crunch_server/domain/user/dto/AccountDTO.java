package com.crunch.crunch_server.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private String account_num;
    private String accountHolder;
    private String bank;

}
