package com.InstaCio.repository;

import com.InstaCio.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> findChatById(Integer chatId);
}
