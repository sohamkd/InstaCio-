package com.InstaCio.dtos;


import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseMsg {

    private String message;
    private boolean success;
    private HttpStatus status;
}
