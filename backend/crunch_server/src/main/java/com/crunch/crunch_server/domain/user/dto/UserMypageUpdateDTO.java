package com.crunch.crunch_server.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Embeddable;

import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserMypageUpdateDTO {
    
    private List<String> interest;
    private String record;
    private MultipartFile image;

    /**
     * @return List<String> return the interest
     */
    public List<String> getInterest() {
        return interest;
    }

    /**
     * @param interest the interest to set
     */
    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    /**
     * @return String return the record
     */
    public String getRecord() {
        return record;
    }

    /**
     * @param record the record to set
     */
    public void setRecord(String record) {
        this.record = record;
    }

    /**
     * @return MultipartFile return the image
     */
    public MultipartFile getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(MultipartFile image) {
        this.image = image;
    }

}
