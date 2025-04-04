package com.InstaCio.services;

import com.InstaCio.dtos.ReelsDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Reels;
import com.InstaCio.entities.User;

import java.util.List;

public interface ReelsService {

    public ReelsDto createReel(ReelsDto reelDto, UserDto userDto);
    public List<ReelsDto> findAllReels();
    public List<ReelsDto> findUsersReel(Integer userId) ;
}
