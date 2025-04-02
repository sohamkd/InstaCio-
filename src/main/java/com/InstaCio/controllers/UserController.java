package com.InstaCio.controllers;

import com.InstaCio.dtos.UserDto;
import com.InstaCio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
    {
        UserDto userDto1 = userService.registerUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findByUserId(@PathVariable("userId") int userId)
    {
        UserDto user = userService.findUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/Email/{userEmail}")
    public ResponseEntity<UserDto> findByUserEmail(@PathVariable String userEmail)
    {
        UserDto userByEmail = userService.findUserByEmail(userEmail);
        return new ResponseEntity<>(userByEmail,HttpStatus.OK);
    }

    @PutMapping("users/{userId1}/{userId2}")
    public ResponseEntity<UserDto> followUser(@PathVariable int userId1,@PathVariable int userId2)
    {
        UserDto userDto = userService.followUser(userId1, userId2);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable int userId)
    {
        UserDto userDto1 = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestParam String query)
    {
        List<UserDto> userDtos = userService.searchUser(query);
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }

}
