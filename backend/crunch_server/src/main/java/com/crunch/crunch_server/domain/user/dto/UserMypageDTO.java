package com.crunch.crunch_server.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserMypageDTO {
    
    private String nickname;
    private List<String> interest;
    private String record;
    private String s3key;

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
     * @return List<String> return the interest
     */
    public List<String> getInterest() {
        return interest;
    }

    /**
     * @param interest the interest to set
     */
    public void setInterest(List<String> interest) {
        this.interest = interest;
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

}
