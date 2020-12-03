package com.crunch.crunch_server.domain.community.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//#endregion import

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="chat")
public class Chat {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private int chatId;
    
    private int communityId;
    private String userNickname;
    private String text;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime time;
    private String tagNickname;


    /**
     * @return int return the chatId
     */
    public int getChatId() {
        return chatId;
    }

    /**
     * @param chatId the chatId to set
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    /**
     * @return int return the communityId
     */
    public int getCommunityId() {
        return communityId;
    }

    /**
     * @param communityId the communityId to set
     */
    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

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
     * @return String return the tagNickname
     */
    public String getTagNickname() {
        return tagNickname;
    }

    /**
     * @param tagNickname the tagNickname to set
     */
    public void setTagNickname(String tagNickname) {
        this.tagNickname = tagNickname;
    }

}
