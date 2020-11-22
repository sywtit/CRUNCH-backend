package com.crunch.crunch_server.domain.commit.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {
    private MultipartFile files;


    /**
     * @return MultipartFile return the files
     */
    public MultipartFile getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(MultipartFile files) {
        this.files = files;
    }

}
