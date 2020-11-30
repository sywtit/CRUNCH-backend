package com.crunch.crunch_server.domain.community.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocketDTO {
    
    private String userName;
    private String content;
  //  private List<String> tagName;


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

    // /**
    //  * @return List<String> return the tagName
    //  */
    // public List<String> getTagName() {
    //     return tagName;
    // }

    // /**
    //  * @param tagName the tagName to set
    //  */
    // public void setTagName(List<String> tagName) {
    //     this.tagName = tagName;
    // }

}
