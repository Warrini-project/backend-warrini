package com.fss.warrini.services;

import com.fss.warrini.entities.UserEntity;
import com.fss.warrini.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }
}
