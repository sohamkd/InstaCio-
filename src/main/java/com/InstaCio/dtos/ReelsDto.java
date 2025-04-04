package com.InstaCio.dtos;

import com.InstaCio.entities.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReelsDto {

    private Long id;

    private String title;

    private String video;

    private User user;
}
