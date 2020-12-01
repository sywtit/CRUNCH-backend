package com.crunch.crunch_server.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EachIndexProfitDTO {
    private int projectId;
    private int postIndexId;
    private String postIndexTitle;
    private int buyerCrewNum;
    private int allProfit;
    private int myProfit;

}
