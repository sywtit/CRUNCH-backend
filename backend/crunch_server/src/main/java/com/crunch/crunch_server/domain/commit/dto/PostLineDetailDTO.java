package com.crunch.crunch_server.domain.commit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostLineDetailDTO {
    
    private String text;
    private String writerName;
    private String s3key;


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
