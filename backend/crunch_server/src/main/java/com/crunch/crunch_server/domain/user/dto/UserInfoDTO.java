package com.crunch.crunch_server.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserInfoDTO implements Serializable{

    private String name;
    private String nickname;
    private String gender;
    private String s3key;
    private String record;
    private int point;



    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return String return the s3key
     */
    public String getS3key() {
        return s3key;
    }

    /**
     * @param s3key the s3key to set
     */
    public void setS3key(String s3key) {
        this.s3key = s3key;
    }

    /**
     * @return String return the record
     */
    public String getRecord() {
        return record;
    }

    /**
     * @param record the record to set
     */
    public void setRecord(String record) {
        this.record = record;
    }

    /**
     * @return int return the point
     */
    public int getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(int point) {
        this.point = point;
    }

}
