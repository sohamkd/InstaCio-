package com.InstaCio.dtos;

import com.InstaCio.entities.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDto {

    private Integer id;

    private String caption;

    private String image;

    private String video;

    private UserDto user;

    private LocalDateTime createdAt;

    private List<UserDto> liked=new ArrayList<>();
}
