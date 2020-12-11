package com.crunch.crunch_server.domain.commit.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlobDTO {
    
    private String writerName;
    private String commit_comment;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime time;
    private String s3key;
    private String post;
    private List<PostLineDetailDTO> postDetailList;
    
    private Boolean modifying;
    private String hisNickname;
    private String hisS3key;


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
     * @return String return the post
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * @return List<PostLineDetail> return the postDetailList
     */
    public List<PostLineDetailDTO> getPostDetailList() {
        return postDetailList;
    }

    /**
     * @param postDetailList the postDetailList to set
     */
    public void setPostDetailList(List<PostLineDetailDTO> postDetailList) {
        this.postDetailList = postDetailList;
    }

    /**
     * @return Boolean return the modifying
     */
    public Boolean isModifying() {
        return modifying;
    }

    /**
     * @param modifying the modifying to set
     */
    public void setModifying(Boolean modifying) {
        this.modifying = modifying;
    }

    /**
     * @return String return the hisNickname
     */
    public String getHisNickname() {
        return hisNickname;
    }

    /**
     * @param hisNickname the hisNickname to set
     */
    public void setHisNickname(String hisNickname) {
        this.hisNickname = hisNickname;
    }

    /**
     * @return String return the hisS3key
     */
    public String getHisS3key() {
        return hisS3key;
    }

    /**
     * @param hisS3key the hisS3key to set
     */
    public void setHisS3key(String hisS3key) {
        this.hisS3key = hisS3key;
    }

}
