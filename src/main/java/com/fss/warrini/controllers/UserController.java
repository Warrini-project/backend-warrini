package com.fss.warrini.controllers;


import com.fss.warrini.dto.AuthResponseDto;
import com.fss.warrini.dto.LoginDto;
import com.fss.warrini.dto.UserDto;
import com.fss.warrini.entities.FacultyEntity;
import com.fss.warrini.entities.UserEntity;
import com.fss.warrini.mappers.UserMapper;
import com.fss.warrini.repositories.FacultyRepo;
import com.fss.warrini.repositories.UserRepo;
import com.fss.warrini.security.JwtGenerator;
import com.fss.warrini.services.CustomUserDetailsService;
import com.fss.warrini.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userServices.addUser(userDto);
        return ResponseEntity.ok().body(savedUser);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateJwt(authentication);
        return ResponseEntity.ok().body(new AuthResponseDto(token, "Bearer "));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userServices.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, Authentication authentication) {
        // Get the authenticated user's ID
        String authenticatedUserUsername = authentication.getName();

        // Check if the username in the path matches the authenticated user's username
        if (!authenticatedUserUsername.equals(userDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can update only your own credentials.");
        }
        return ResponseEntity.ok().body(userServices.updateUser(userDto));
    }
}
