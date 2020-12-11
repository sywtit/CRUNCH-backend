package com.crunch.crunch_server.domain.community.service;

import com.crunch.crunch_server.domain.community.dto.SocketDTO;
import com.crunch.crunch_server.domain.community.entity.Chat;
import com.crunch.crunch_server.domain.community.mapper.ChatMapper;
import com.crunch.crunch_server.domain.community.repository.ChatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

	public void saveChat(String roomId, SocketDTO chatMessage) {

        if(chatMessage.getTagName().size() == 0)
        {
            Chat chat = new Chat();
            chat.setCommunityId(Integer.parseInt(roomId));
            chat.setTagNickname(null);
            chat.setText(chatMessage.getContent());
            chat.setTime(chatMessage.getTime());
            chat.setUserNickname(chatMessage.getUserName());
        
            chatRepository.save(chat);
        }

        else
        {
            for(int i = 0; i<chatMessage.getTagName().size(); i++)
            {
                Chat chat = ChatMapper.Instance.toChatEntity(roomId, chatMessage.getTagName().get(i).getName(), chatMessage);
                chatRepository.save(chat);

            }
        }


	}
    
}
