package com.crunch.crunch_server.domain.crew.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WriterCrewDetailDTO {
    
    private String writerName;
    
    
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

}
