package com.crunch.crunch_server.domain.crew.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WriterMoneyPercentDTO {
    private int userId;
    private String writerNick;
    private int selectprofit;

}
