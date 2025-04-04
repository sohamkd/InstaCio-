package com.InstaCio.services;

import com.InstaCio.dtos.ChatDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Chat;

import java.util.List;

public interface ChatService {

    public ChatDto createChat(UserDto reqUser, UserDto user2) ;

    public ChatDto findChatById(Integer chatId);

    public List<ChatDto> findUsersChat(Integer userId) ;


   /* public Chat createGroup(GroupChatRequest req,Integer reqUerId) throws UserException;

    public Chat addUserToGroup(Integer userId, Integer chatId) ;

    public Chat renameGroup(Integer chatId,String groupName, Integer reqUserId) ;

    public Chat removeFromGroup(Integer chatId,Integer userId, Integer reqUser);

    public Chat deleteChat(Integer chatId, Integer userId);*/
}
