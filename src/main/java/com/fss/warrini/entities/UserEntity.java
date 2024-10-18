package com.fss.warrini.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String domain_studies;
    private String level_studies;

    @ManyToOne
    private FacultyEntity faculty;

    private boolean want_notified;


}