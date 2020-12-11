package com.crunch.crunch_server.domain.community.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocketDTO {
    
    private String userName;
    private String content;
    private List<TagNameDTO> tagName;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime time;

    //state
    //url
    
    /**
     * @return String return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return String return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return List<String> return the tagName
     */
    public List<TagNameDTO> getTagName() {
        return tagName;
    }

    /**
     * @param tagName the tagName to set
     */
    public void setTagName(List<TagNameDTO> tagName) {
        this.tagName = tagName;
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

}
