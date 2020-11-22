package com.crunch.crunch_server.domain.commit.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyDTO {
    
    private String after;


    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime time;
    
    private String commit_comment;

    //private List<MultipartFile> files;

   // private MultipartFile[] files;

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
     * @return LocalDateTime return the time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(LocalDateTime time) {
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
