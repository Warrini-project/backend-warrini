package com.fss.warrini.services;

import com.fss.warrini.dto.UserDto;
import com.fss.warrini.entities.UserEntity;

public interface UserServices {
    UserDto addUser(UserDto userDto);
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
}
