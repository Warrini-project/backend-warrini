package com.fss.warrini.controllers;


import com.fss.warrini.dto.LoginDto;
import com.fss.warrini.dto.UserDto;
import com.fss.warrini.entities.FacultyEntity;
import com.fss.warrini.entities.UserEntity;
import com.fss.warrini.mappers.UserMapper;
import com.fss.warrini.repositories.FacultyRepo;
import com.fss.warrini.repositories.UserRepo;
import com.fss.warrini.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDto user) {
        UserDto savedUser = userServices.addUser(userMapper.toEntity(user));
        return ResponseEntity.ok().body(savedUser);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto+"\n\n\n");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().body(authentication);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userServices.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
