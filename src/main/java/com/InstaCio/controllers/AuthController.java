package com.InstaCio.controllers;

import com.InstaCio.config.JwtProvider;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.User;
import com.InstaCio.exceptions.ResourceNotFoundException;
import com.InstaCio.repository.UserRepository;
import com.InstaCio.request.LoginRequest;
import com.InstaCio.response.AuthResponse;
import com.InstaCio.services.CustomUserDetailsService;
import com.InstaCio.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetails;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody UserDto userDto) throws Exception {

        User user = modelMapper.map(userDto, User.class);
        System.out.println(user.getEmail());
        Optional<User> isExist=userRepository.findByEmail(user.getEmail());


        if(isExist.isPresent())
        {
            throw new Exception("this email is already used with another account");
        }


        User newUser=new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setGender(user.getGender());

        User savedUser = userRepository.save(newUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());

        String token= JwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse(token,"Register success");
        return authResponse;
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token= JwtProvider.generateToken(authentication);

        AuthResponse res=new AuthResponse(token,"Login success");
        return res;
    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails=customUserDetails.loadUserByUsername(email);
        if(userDetails==null)
        {
            throw new BadCredentialsException("Invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}