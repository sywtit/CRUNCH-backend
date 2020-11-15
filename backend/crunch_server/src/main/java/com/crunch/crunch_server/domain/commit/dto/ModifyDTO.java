package com.crunch.crunch_server.domain.commit.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyDTO {
    
    private String after;
    private Date time;
    private String commit_comment;

    /**
     * @return String return the after
     */
    public String getAfter() {
        return after;
    }

    /**
     * @param after the after to set
     */
    public void setAfter(String after) {
        this.after = after;
    }

    /**
     * @return Date return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return String return the commit_comment
     */
    public String getCommit_comment() {
        return commit_comment;
    }

    /**
     * @param commit_comment the commit_comment to set
     */
    public void setCommit_comment(String commit_comment) {
        this.commit_comment = commit_comment;
    }

}
