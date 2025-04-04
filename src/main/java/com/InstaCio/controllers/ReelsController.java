package com.InstaCio.controllers;

import com.InstaCio.dtos.ReelsDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Reels;
import com.InstaCio.entities.User;
import com.InstaCio.services.ReelsService;
import com.InstaCio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<ReelsDto> createReels(@RequestBody ReelsDto reelsDto,
                                                @RequestHeader("Authorization") String jwt){
        UserDto userByJwt = userService.findUserByJwt(jwt);
        ReelsDto reel = reelsService.createReel(reelsDto, userByJwt);
        return new ResponseEntity<>(reel, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<ReelsDto>> getAllReels(
            @RequestHeader("Authorization") String jwt) throws Exception{
        UserDto user =userService.findUserByJwt(jwt);
        List<ReelsDto> reels=reelsService.findAllReels();

        return new ResponseEntity<>(reels,HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReelsDto>> getUsersReels(
            @RequestHeader("Authorization") String jwt,@PathVariable Integer userId) throws Exception{
        UserDto user =userService.findUserByJwt(jwt);
        List<ReelsDto> reels=reelsService.findUsersReel(userId);

        return new ResponseEntity<>(reels,HttpStatus.ACCEPTED);
    }
}
