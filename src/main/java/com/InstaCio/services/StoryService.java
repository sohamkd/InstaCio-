package com.InstaCio.services;

import com.InstaCio.dtos.StoryDto;
import com.InstaCio.dtos.UserDto;

import java.util.List;

public interface StoryService {

    public StoryDto createStory(StoryDto storyDto, UserDto userDto) ;

    public List<StoryDto> findStoryByUserId(Integer userId);
}
