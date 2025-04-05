package com.InstaCio.controllers;

import com.InstaCio.dtos.StoryDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.services.StoryService;
import com.InstaCio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public ResponseEntity<StoryDto> createStoryHandler(@RequestBody StoryDto storyDto, @RequestHeader("Authorization") String token) {

        UserDto reqUser=userService.findUserByJwt(token);

        StoryDto createdStory =storyService.createStory(storyDto, reqUser);
        return new ResponseEntity<StoryDto>(createdStory, HttpStatus.OK);
    }


    @GetMapping("/api/story/user/{userId}")
    public ResponseEntity<List<StoryDto>> findStoryByUserId(@PathVariable Integer userId) throws Exception {

        List<StoryDto> stories= storyService.findStoryByUserId(userId);

        System.out.println("stories userid --------- ");

        return new ResponseEntity<List<StoryDto>>(stories, HttpStatus.OK);
    }
}
