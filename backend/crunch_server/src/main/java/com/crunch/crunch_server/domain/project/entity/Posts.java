package com.crunch.crunch_server.domain.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name="posts")
public class Posts {
    
    @Id
    @GeneratedValue
    private int id;
    //@Column(name="index_id")
    private Integer indexId;
   // @Column(name="project_id")
    private Integer projectId;
    private String complete_post;
    
}
