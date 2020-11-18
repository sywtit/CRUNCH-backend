package com.crunch.crunch_server.domain.commit.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommitHistoryDTO {
    
    private int commitId;
    private String writerName;
    private String commit_comment;
    private Date time;


    /**
     * @return int return the commitId
     */
    public int getCommitId() {
        return commitId;
    }

    /**
     * @param commitId the commitId to set
     */
    public void setCommitId(int commitId) {
        this.commitId = commitId;
    }

    /**
     * @return String return the writerName
     */
    public String getWriterName() {
        return writerName;
    }

    /**
     * @param writerName the writerName to set
     */
    public void setWriterName(String writerName) {
        this.writerName = writerName;
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

}
