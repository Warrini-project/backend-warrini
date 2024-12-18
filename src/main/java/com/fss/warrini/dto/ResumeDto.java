package com.fss.warrini.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResumeDto {
    private String name;
    private String profession;
    private String address;
    private String phone;
    private String email;
    private String linkedin;
    private String github;
    private String summary;
    private List<EducationDto> educations;
    private List<String> languages;
    private List<ExperienceDto> experiences;
    private List<String> skills;
    private List<String> certifications;
}

