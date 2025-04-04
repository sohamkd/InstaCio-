package com.InstaCio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String chat_name;
    private String chat_image;

    /*  private Boolean is_group;

      @ManyToOne
      private User created_by;
  */
    @ManyToMany
    private List<User> users = new ArrayList<>();

    private LocalDateTime timestamp;

   /* @OneToMany(mappedBy = "chat")
    private List<Message> messages=new ArrayList<>();*/
}