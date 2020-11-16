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
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String return the introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction the introduction to set
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * @return int return the process
     */
    public int getProcess() {
        return process;
    }

    /**
     * @param process the process to set
     */
    public void setProcess(int process) {
        this.process = process;
    }

    /**
     * @return int return the mwn
     */
    public int getMwn() {
        return mwn;
    }

    /**
     * @param mwn the mwn to set
     */
    public void setMwn(int mwn) {
        this.mwn = mwn;
    }

    /**
     * @return String return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return double return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return String return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return int return the freeornot
     */
    public int getFreeornot() {
        return freeornot;
    }

    /**
     * @param freeornot the freeornot to set
     */
    public void setFreeornot(int freeornot) {
        this.freeornot = freeornot;
    }

    /**
     * @return String return the complete_time
     */
    public String getComplete_time() {
        return complete_time;
    }

    /**
     * @param complete_time the complete_time to set
     */
    public void setComplete_time(String complete_time) {
        this.complete_time = complete_time;
    }

    /**
     * @return int return the target_funding_money
     */
    public int getTarget_funding_money() {
        return target_funding_money;
    }

    /**
     * @param target_funding_money the target_funding_money to set
     */
    public void setTarget_funding_money(int target_funding_money) {
        this.target_funding_money = target_funding_money;
    }

    /**
     * @return String return the target_d_day
     */
    public String getTarget_d_day() {
        return target_d_day;
    }

    /**
     * @param target_d_day the target_d_day to set
     */
    public void setTarget_d_day(String target_d_day) {
        this.target_d_day = target_d_day;
    }

}
