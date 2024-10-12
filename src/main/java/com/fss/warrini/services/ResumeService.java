package com.fss.warrini.services;

import com.fss.warrini.dto.ResumeDto;

import java.io.File;

public interface ResumeService {
    byte[] generateTemplate1(ResumeDto resumeDto);
}
