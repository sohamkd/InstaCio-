package com.InstaCio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String gender;

    private List<Integer> followers=new ArrayList<>();

    private List<Integer> followings=new ArrayList<>();

    //Many users can save many posts
    @ManyToMany
    private List<Post> savedPost=new ArrayList<>();


}
