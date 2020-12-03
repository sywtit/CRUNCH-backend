package com.crunch.crunch_server.domain.community.dto;

import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.community.entity.Chat;
import com.crunch.crunch_server.domain.crew.entity.WritersCrew;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FirstCommunityBlobDTO {
    
    private BlobDTO blobDTO;
    private List<Chat> chat;
    private List<WritersCrew> writerCrew;
    
    /**
     * @return BlobDTO return the blobDTO
     */
    public BlobDTO getBlobDTO() {
        return blobDTO;
    }

    /**
     * @param blobDTO the blobDTO to set
     */
    public void setBlobDTO(BlobDTO blobDTO) {
        this.blobDTO = blobDTO;
    }

    /**
     * @return List<Chat> return the chat
     */
    public List<Chat> getChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }

    /**
     * @return List<WritersCrew> return the writerCrew
     */
    public List<WritersCrew> getWriterCrew() {
        return writerCrew;
    }

    /**
     * @param writerCrew the writerCrew to set
     */
    public void setWriterCrew(List<WritersCrew> writerCrew) {
        this.writerCrew = writerCrew;
    }

}
