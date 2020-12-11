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
public class BlobChatDTO {
    
    private String userNickname;
    private String text;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime time;
    private List<TagNameDTO> tagNickname;



    /**
     * @return String return the userNickname
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * @param userNickname the userNickname to set
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
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
     * @return List<TagNameDTO> return the tagNickname
     */
    public List<TagNameDTO> getTagNickname() {
        return tagNickname;
    }

    /**
     * @param tagNickname the tagNickname to set
     */
    public void setTagNickname(List<TagNameDTO> tagNickname) {
        this.tagNickname = tagNickname;
    }

}
