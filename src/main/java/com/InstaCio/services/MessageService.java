package com.InstaCio.services;

import com.InstaCio.dtos.ChatDto;
import com.InstaCio.dtos.MessageDto;
import com.InstaCio.dtos.UserDto;

import java.util.List;


public interface MessageService {

    public MessageDto createMessage(UserDto userDto, Integer chatId, MessageDto req) ;

    public List<MessageDto> findChatsMessages(Integer chatId) ;

}
