package com.fss.warrini.services;

import com.fss.warrini.dto.UserDto;
import com.fss.warrini.entities.UserEntity;

public interface UserServices {
    UserDto addUser(UserEntity user);
    UserDto updateUser(UserEntity user);
    void deleteUser(Long userId);
}
