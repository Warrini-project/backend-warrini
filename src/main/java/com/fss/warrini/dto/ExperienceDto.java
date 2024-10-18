package com.fss.warrini.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExperienceDto {
    private String name;
    private String place;
    private String duration;
    private List<String> tasks;
}
