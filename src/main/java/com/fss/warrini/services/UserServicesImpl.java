package com.fss.warrini.services;

import com.fss.warrini.dto.UserDto;
import com.fss.warrini.entities.FacultyEntity;
import com.fss.warrini.entities.UserEntity;
import com.fss.warrini.exceptions.FacultyNotFoundException;
import com.fss.warrini.exceptions.UserNameAlreadyUsedException;
import com.fss.warrini.exceptions.UserNotFoundException;
import com.fss.warrini.mappers.UserMapper;
import com.fss.warrini.repositories.FacultyRepo;
import com.fss.warrini.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FacultyRepo facultyRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto addUser(UserEntity user) {
         userRepo.findByUsername(user.getUsername()).ifPresent(foundedUser -> {
             throw new UserNameAlreadyUsedException("Username already used");
         });
         facultyRepo.findById(user.getFaculty().getId()).orElseThrow(() -> new FacultyNotFoundException("Faculty not found"));
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userMapper.toDto(userRepo.save(user));
    }

    @Override
    public UserDto updateUser(UserEntity user) {
        return userMapper.toDto(userRepo.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepo.deleteById(userId);
    }
}
