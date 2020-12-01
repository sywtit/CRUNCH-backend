package com.crunch.crunch_server.domain.community.entity;

//#region import
import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//#endregion import

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="community")
public class Community {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private int id;
    
    private int postindexId;
    private int projectId;
    private int userId;




    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return int return the postindexId
     */
    public int getPostindexId() {
        return postindexId;
    }

    /**
     * @param postindexId the postindexId to set
     */
    public void setPostindexId(int postindexId) {
        this.postindexId = postindexId;
    }

    /**
     * @return int return the projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * @return int return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
