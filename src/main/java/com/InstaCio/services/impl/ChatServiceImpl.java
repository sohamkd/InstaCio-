package com.InstaCio.services.impl;

import com.InstaCio.dtos.ChatDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Chat;
import com.InstaCio.entities.User;
import com.InstaCio.exceptions.ResourceNotFoundException;
import com.InstaCio.repository.ChatRepository;
import com.InstaCio.services.ChatService;
import com.InstaCio.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ChatDto createChat(UserDto reqUser, UserDto user2) {

        User reqUser1 = modelMapper.map(reqUser, User.class);
        User user1 = modelMapper.map(user2, User.class);

        Chat chatByUserId = chatRepository.findChatByUserId(user1, reqUser1);

        if(chatByUserId!=null)
        {
            return modelMapper.map(chatByUserId, ChatDto.class);
        }

        Chat chat=new Chat();
        chat.getUsers().add(user1);
        chat.getUsers().add(reqUser1);
        chat.setTimestamp(LocalDateTime.now());
        Chat savedChat = chatRepository.save(chat);
        return modelMapper.map(savedChat, ChatDto.class);

    }

    @Override
    public ChatDto findChatById(Integer chatId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new ResourceNotFoundException("chat with given id not found"));
        return modelMapper.map(chat, ChatDto.class);
    }

    @Override
    public List<ChatDto> findUsersChat(Integer userId) {
        List<Chat> chats=chatRepository.findByUsersId(userId);
        List<ChatDto> chatDto = chats.stream().map(chat -> new ModelMapper().map(chat, ChatDto.class)).collect(Collectors.toList());
        return chatDto;
    }
}
