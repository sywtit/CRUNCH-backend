package com.crunch.crunch_server.domain.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="posts")
public class Posts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( name = "indexId", insertable=false, updatable=false)
    private Integer index_id;

    @Column(name="projectId", insertable=false, updatable=false)
    private Integer project_id;

    private String complete_post;

    private Integer modifying;

    private Integer modifyingUserId;
    


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
     * @return Integer return the index_id
     */
    public Integer getIndex_id() {
        return index_id;
    }

    /**
     * @param index_id the index_id to set
     */
    public void setIndex_id(Integer index_id) {
        this.index_id = index_id;
    }

    /**
     * @return Integer return the project_id
     */
    public Integer getProject_id() {
        return project_id;
    }

    /**
     * @param project_id the project_id to set
     */
    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    /**
     * @return String return the complete_post
     */
    public String getComplete_post() {
        return complete_post;
    }

    /**
     * @param complete_post the complete_post to set
     */
    public void setComplete_post(String complete_post) {
        this.complete_post = complete_post;
    }

    /**
     * @return Integer return the modifying
     */
    public Integer getModifying() {
        return modifying;
    }

    /**
     * @param modifying the modifying to set
     */
    public void setModifying(Integer modifying) {
        this.modifying = modifying;
    }

    /**
     * @return Integer return the modifyingUserId
     */
    public Integer getModifyingUserId() {
        return modifyingUserId;
    }

    /**
     * @param modifyingUserId the modifyingUserId to set
     */
    public void setModifyingUserId(Integer modifyingUserId) {
        this.modifyingUserId = modifyingUserId;
    }

}
