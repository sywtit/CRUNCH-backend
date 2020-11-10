package com.crunch.crunch_server.domain.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name="project")
public class Project {
    
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String introduction;
    private int process;
    private int mwn;
    private String state;
    private double rating;
    private String genre;
    private int freeornot;
    private String complete_time;
    private int target_funding_money;
    private String target_d_day;
    

}
