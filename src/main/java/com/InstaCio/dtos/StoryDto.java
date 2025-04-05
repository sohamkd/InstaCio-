package com.InstaCio.dtos;

import com.InstaCio.entities.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryDto {

    private Integer id;

    private User user;

    private String image;

    private String captions;

    private LocalDateTime timestamp;
}
