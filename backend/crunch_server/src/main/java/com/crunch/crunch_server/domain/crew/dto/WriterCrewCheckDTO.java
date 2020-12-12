package com.crunch.crunch_server.domain.crew.dto;

import java.sql.Date;

import com.crunch.crunch_server.domain.crew.entity.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WriterCrewCheckDTO {

    private int projectId;
    private int userId;
    private State state;
    private int mainornot;
    private int limit_status;
    private Date limit_status_date;

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

    /**
     * @return State return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return int return the mainornot
     */
    public int getMainornot() {
        return mainornot;
    }

    /**
     * @param mainornot the mainornot to set
     */
    public void setMainornot(int mainornot) {
        this.mainornot = mainornot;
    }

    /**
     * @return int return the limit_status
     */
    public int getLimit_status() {
        return limit_status;
    }

    /**
     * @param limit_status the limit_status to set
     */
    public void setLimit_status(int limit_status) {
        this.limit_status = limit_status;
    }

    /**
     * @return Date return the limit_status_date
     */
    public Date getLimit_status_date() {
        return limit_status_date;
    }

    /**
     * @param limit_status_date the limit_status_date to set
     */
    public void setLimit_status_date(Date limit_status_date) {
        this.limit_status_date = limit_status_date;
    }

}
