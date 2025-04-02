package com.InstaCio.services;

import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.User;

import java.util.List;

public interface UserService {

    public UserDto registerUser(UserDto userDto);

    public UserDto findUserById(Integer userId);

    public UserDto findUserByEmail(String email);

    public UserDto followUser(Integer userId1,Integer userId2);

    public UserDto updateUser(UserDto userDto,int userId);

    public List<UserDto> searchUser(String query);

//    public User findUserByJwt(String jwt);

}
