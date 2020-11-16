package com.crunch.crunch_server.domain.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="postindex")
public class PostIndex {

    @Id
    @GeneratedValue
    private int id;

    @Column( name = "projectId", insertable=false, updatable=false)
    private int projectId;
    private String title;
    private int fee;
    

}
