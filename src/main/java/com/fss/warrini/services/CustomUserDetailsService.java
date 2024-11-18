package com.fss.warrini.services;

import com.fss.warrini.entities.UserEntity;
import com.fss.warrini.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));

        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRoles().stream().map(
                SimpleGrantedAuthority::new
        ).collect(Collectors.toSet()));
    }
}
