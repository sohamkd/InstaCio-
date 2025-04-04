package com.InstaCio.services.impl;

import com.InstaCio.dtos.ReelsDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Reels;
import com.InstaCio.entities.User;
import com.InstaCio.repository.ReelsRepository;
import com.InstaCio.services.ReelsService;
import com.InstaCio.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReelsServiceImpl implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReelsDto createReel(ReelsDto reelDto, UserDto userDto) {

        Reels reels = modelMapper.map(reelDto, Reels.class);
        User user = modelMapper.map(userDto, User.class);
        reels.setUser(user);

        Reels savedReel = reelsRepository.save(reels);

        return modelMapper.map(savedReel,ReelsDto.class);
    }

    @Override
    public List<ReelsDto> findAllReels() {
        List<Reels> allReels = reelsRepository.findAll();
        List<ReelsDto> allReelsDto = allReels.stream().map(reel -> new ModelMapper().map(reel, ReelsDto.class)).collect(Collectors.toList());
        return allReelsDto;
    }

    @Override
    public List<ReelsDto> findUsersReel(Integer userId) {
        List<Reels> allReels = reelsRepository.findByUserId(userId);
        List<ReelsDto> allReelsDto = allReels.stream().map(reel -> new ModelMapper().map(reel, ReelsDto.class)).collect(Collectors.toList());
        return allReelsDto;
    }
}
