package com.InstaCio.services.impl;

import com.InstaCio.dtos.StoryDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Story;
import com.InstaCio.entities.User;
import com.InstaCio.repository.StoryRepository;
import com.InstaCio.repository.UserRepository;
import com.InstaCio.services.StoryService;
import com.InstaCio.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StoryDto createStory(StoryDto storyDto, UserDto userDto) {

        Story createdStory = modelMapper.map(storyDto, Story.class);
        User user = modelMapper.map(userDto, User.class);

        createdStory.setCaptions(storyDto.getCaptions());
        createdStory.setImage(storyDto.getImage());
        createdStory.setUser(user);
        createdStory.setTimestamp(LocalDateTime.now());
        Story savedStory = storyRepo.save(createdStory);

        return modelMapper.map(savedStory,StoryDto.class);
    }

    @Override
    public List<StoryDto> findStoryByUserId(Integer userId) {
        UserDto userById = userService.findUserById(userId);
        List<Story> storyList = storyRepo.findByUserId(userId);
        List<StoryDto> collect = storyList.stream().map(story -> new ModelMapper().map(story, StoryDto.class)).collect(Collectors.toList());
        return collect;
    }
}
