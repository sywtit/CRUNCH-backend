package com.crunch.crunch_server.domain.community.dto;

import java.util.List;

import com.crunch.crunch_server.domain.commit.dto.BlobDTO;
import com.crunch.crunch_server.domain.community.entity.Chat;
import com.crunch.crunch_server.domain.crew.dto.WriterCrewDetailDTO;
import com.crunch.crunch_server.domain.crew.entity.WritersCrew;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FirstCommunityBlobDTO {
    
    private BlobDTO blobDTO;
    private List<BlobChatDTO> chat;
    private List<WriterCrewDetailDTO> writerCrew;
    private int roomId;
    

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
     * @return List<BlobChatDTO> return the chat
     */
    public List<BlobChatDTO> getChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(List<BlobChatDTO> chat) {
        this.chat = chat;
    }

    /**
     * @return List<WriterCrewDetailDTO> return the writerCrew
     */
    public List<WriterCrewDetailDTO> getWriterCrew() {
        return writerCrew;
    }

    /**
     * @param writerCrew the writerCrew to set
     */
    public void setWriterCrew(List<WriterCrewDetailDTO> writerCrew) {
        this.writerCrew = writerCrew;
    }

    /**
     * @return int return the roomId
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

}
