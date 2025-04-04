package com.InstaCio.repository;

import com.InstaCio.entities.Chat;
import com.InstaCio.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {


    public List<Chat> findByUsersId(Integer userId);

    @Query("select c from Chat c Where :user Member of c.users And :reqUser Member of c.users")
    public Chat findChatByUserId(@Param("user") User user, @Param("reqUser")User reqUser);
}
