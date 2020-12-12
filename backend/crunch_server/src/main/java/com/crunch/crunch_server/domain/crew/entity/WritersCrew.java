package com.crunch.crunch_server.domain.crew.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "writerscrew")
public class WritersCrew {

    @EmbeddedId
    private WriterCrewIdentity writerCrewIdentity;

    private String comment;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    private int mainornot;
    private int money_percent;
    private int limit_status;
    private Date limit_status_date;

    /**
     * @return String return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
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
     * @return int return the money_percent
     */
    public int getMoney_percent() {
        return money_percent;
    }

    /**
     * @param money_percent the money_percent to set
     */
    public void setMoney_percent(int money_percent) {
        this.money_percent = money_percent;
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
