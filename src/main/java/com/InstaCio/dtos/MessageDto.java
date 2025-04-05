package com.InstaCio.dtos;

import com.InstaCio.entities.Chat;
import com.InstaCio.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private Integer id;

    private String content;

    private String image;

    private LocalDateTime timeStamp;

    private User user;

    private Chat chat;

}
