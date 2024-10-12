package com.fss.warrini.dto;

import lombok.Data;

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
    private Long faculty;
    private boolean want_notified;
}
