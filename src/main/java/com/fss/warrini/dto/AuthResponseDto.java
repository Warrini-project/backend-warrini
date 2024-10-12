package com.fss.warrini.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {

    String accessToken;
    String tokenType;
}
