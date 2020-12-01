package com.crunch.crunch_server.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EachMonthProfitDTO {
    private int month;
    private int myProfit;
    
}
