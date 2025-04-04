package com.InstaCio.services.impl;

import com.InstaCio.config.JwtProvider;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.User;
import com.InstaCio.exceptions.ResourceNotFoundException;
import com.InstaCio.repository.UserRepository;
import com.InstaCio.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto findUserById(Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found"));
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with given email not found"));
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto followUser(Integer reqUserId, Integer userId2) {

        User reqUser1 = userRepository.findById(reqUserId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found"));
        User user2 = userRepository.findById(userId2).orElseThrow(() -> new ResourceNotFoundException("User with given id not found"));

        user2.getFollowers().add(reqUser1.getId());
        reqUser1.getFollowings().add(user2.getId());

        userRepository.save(reqUser1);
        userRepository.save(user2);
        return modelMapper.map(reqUser1, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found"));
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<UserDto> searchUser(String query) {

        List<User> users = userRepository.searchUser(query);
        List<UserDto> dtoList = users.stream().map(user ->new ModelMapper().map(user,UserDto.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public UserDto findUserByJwt(String jwt) {

        String email= JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("user not found with given email"));
        return modelMapper.map(user,UserDto.class);
    }
}
