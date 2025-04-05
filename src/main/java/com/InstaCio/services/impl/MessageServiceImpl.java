package com.InstaCio.services.impl;

import com.InstaCio.dtos.ChatDto;
import com.InstaCio.dtos.MessageDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Chat;
import com.InstaCio.entities.Message;
import com.InstaCio.entities.User;
import com.InstaCio.repository.ChatRepository;
import com.InstaCio.repository.MessageRepository;
import com.InstaCio.services.ChatService;
import com.InstaCio.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public MessageDto createMessage(UserDto userDto, Integer chatId, MessageDto req) {

        User user = modelMapper.map(userDto, User.class);

        ChatDto chatById = chatService.findChatById(chatId);
        Chat chat = modelMapper.map(chatById, Chat.class);

        Message reqM = modelMapper.map(req, Message.class);

        Message message=new Message();
        message.setChat(chat);
        message.setContent(reqM.getContent());
        message.setImage(reqM.getImage());
        message.setUser(user);
        message.setTimeStamp(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return modelMapper.map(savedMessage,MessageDto.class);
    }

    @Override
    public List<MessageDto> findChatsMessages(Integer chatId) {
        ChatDto chatById = chatService.findChatById(chatId);
        List<Message> chatById1 = messageRepository.findChatById(chatId);
        List<MessageDto> msg = chatById1.stream().map(chat -> new ModelMapper().map(chat, MessageDto.class)).collect(Collectors.toList());
        return msg;
    }
}
