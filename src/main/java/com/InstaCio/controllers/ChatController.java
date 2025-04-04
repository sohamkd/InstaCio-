package com.InstaCio.controllers;

import com.InstaCio.dtos.ChatDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Chat;
import com.InstaCio.request.CreateChatRequest;
import com.InstaCio.services.ChatService;
import com.InstaCio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public ChatDto createChat(@RequestBody CreateChatRequest req, @RequestHeader("Authorization") String jwt) throws Exception
    {
        UserDto user = userService.findUserByJwt(jwt);
        UserDto user2 = userService.findUserById(req.getUserId());
        ChatDto chatDto=chatService.createChat(user,user2);
        return chatDto;
    }

    @GetMapping("/api/chats")
    public List<ChatDto> findUsersChat(@RequestHeader("Authorization") String jwt)
    {
        UserDto user = userService.findUserByJwt(jwt);
        List<ChatDto> chatDtos=chatService.findUsersChat(user.getId());
        return chatDtos;
    }

    @GetMapping("/api/chats/{chatId}")
    public ChatDto findChatById(@PathVariable int chatId)
    {
        ChatDto chatById = chatService.findChatById(chatId);
        return chatById;
    }
}
