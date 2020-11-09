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
@Table(name="post")
public class Post {
    
    @Id
    @GeneratedValue
    private int id;
    private int index_id;
    private int project_id;
    private String complete_post;
    

}
