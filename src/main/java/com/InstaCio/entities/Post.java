package com.InstaCio.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;

    private String image;

    private String video;

    //Many posts can be created by one user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private LocalDateTime createdAt;


    //One post can be liked by multiple users
    @OneToMany
    private List<User> liked=new ArrayList<>();

    /*
    @OneToMany
    private List<Comment> comments=new ArrayList<>();
       */
}