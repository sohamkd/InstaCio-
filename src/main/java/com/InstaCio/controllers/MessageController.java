package com.InstaCio.controllers;

import com.InstaCio.dtos.MessageDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.services.MessageService;
import com.InstaCio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public MessageDto createMessage(@RequestBody MessageDto req, @RequestHeader("Authorization") String jwt
            , @PathVariable Integer chatId) throws Exception {
        UserDto userDto = userService.findUserByJwt(jwt);
        MessageDto messageDto = messageService.createMessage(userDto, chatId, req);
        return messageDto;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<MessageDto> findChatsMessages(@RequestHeader("Authorization") String jwt
            , @PathVariable Integer chatId) throws Exception {
        UserDto user = userService.findUserByJwt(jwt);
        List<MessageDto> chatsMessages = messageService.findChatsMessages(chatId);
        return chatsMessages;
    }
}
