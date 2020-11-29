package com.crunch.crunch_server.domain.commit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="postLineDetail")
public class PostLineDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private int id;
    
    private int postId;
    
    private String text;
    private String writerName;
    private int lineNum;
    private String s3key;


    /**
     * @return int return the postId
     */
    public int getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * @return String return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
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
     * @return int return the lineNum
     */
    public int getLineNum() {
        return lineNum;
    }

    /**
     * @param lineNum the lineNum to set
     */
    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
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
