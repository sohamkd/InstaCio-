package com.InstaCio.controllers;

import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.User;
import com.InstaCio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserService userService;


    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserDto> findByUserId(@PathVariable("userId") int userId)
    {
        UserDto user = userService.findUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/api/users/Email/{userEmail}")
    public ResponseEntity<UserDto> findByUserEmail(@PathVariable String userEmail)
    {
        UserDto userByEmail = userService.findUserByEmail(userEmail);
        return new ResponseEntity<>(userByEmail,HttpStatus.OK);
    }

    @PutMapping("/api/users/{userId2}")
    public ResponseEntity<UserDto> followUser(@RequestHeader("Authorization") String jwt,@PathVariable int userId2)
    {
        UserDto reqUser = userService.findUserByJwt(jwt);
        UserDto userDto = userService.followUser(reqUser.getId(), userId2);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    //we will update user who is logged in
    //one user cannot update other users info
    @PutMapping("/api/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@RequestHeader("Authorization") String jwt)
    {
        UserDto userByJwt = userService.findUserByJwt(jwt);
        UserDto userDto1 = userService.updateUser(userDto, userByJwt.getId());
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }

    @GetMapping("api/users/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestParam String query)
    {
        List<UserDto> userDtos = userService.searchUser(query);
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }



    @GetMapping("api/users/profile")
    public UserDto getUserFromToken(@RequestHeader("Authorization") String jwt)
    {
        //System.out.println("jwt-----"+jwt);

        UserDto user = userService.findUserByJwt(jwt);
        user.setPassword(null);

        return user;
    }
}
