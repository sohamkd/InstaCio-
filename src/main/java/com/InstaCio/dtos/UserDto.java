package com.InstaCio.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String gender;

    private List<Integer> followers=new ArrayList<>();

    private List<Integer> followings=new ArrayList<>();

    @JsonIgnore
    private List<PostDto> savedPost=new ArrayList<>();
}
