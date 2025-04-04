package com.InstaCio.dtos;


import com.InstaCio.entities.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Integer id;

    private String content;

    private User user;

    private Set<User> liked= new HashSet<>();

    private LocalDateTime createdAt;

}
