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
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
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
    public UserDto addUser(UserDto userDto) {
         userRepo.findByUsername(userDto.getUsername()).ifPresent(foundedUser -> {
             throw new UserNameAlreadyUsedException("Username already used");
         });
         facultyRepo.findById(userDto.getFacultyId()).orElseThrow(() -> new FacultyNotFoundException("Faculty not found"));

         UserEntity userEntity = userMapper.toEntity(userDto);
         userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
         userEntity.setRoles(new HashSet<>(Arrays.asList("USER")));
         return userMapper.toDto(userRepo.save(userEntity));
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {

        UserEntity userEntity = userRepo.findByUsername(userDto.getUsername()).orElseThrow(() -> new UserNotFoundException());
        if (userDto.getPassword() != null && !passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        } else {
            userDto.setPassword(userEntity.getPassword());
        }
        userDto.setId(userEntity.getId());

        return userMapper.toDto(userRepo.save(userMapper.toEntity(userDto)));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
        userRepo.deleteById(userId);
    }
}
