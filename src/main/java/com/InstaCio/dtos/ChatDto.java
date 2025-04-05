package com.InstaCio.dtos;

import com.InstaCio.entities.Message;
import com.InstaCio.entities.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private Integer id;

    private String chat_name;
    private String chat_image;

    private List<User> users = new ArrayList<>();

    private LocalDateTime timestamp;

    private List<Message> messages=new ArrayList<>();
}
