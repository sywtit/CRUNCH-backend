package com.crunch.crunch_server.domain.crew.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class WriterCrewIdentity implements Serializable {

    private int projectId;
    private int userId;


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
