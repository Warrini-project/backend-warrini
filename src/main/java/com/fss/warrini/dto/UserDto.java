package com.fss.warrini.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String domain_studies;
    private String level_studies;
    private Long facultyId;
    private boolean want_notified;
    private Set<String> roles = new HashSet<>();
}
