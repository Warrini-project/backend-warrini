package com.fss.warrini.services;

import com.fss.warrini.PdfGenerators.Template1Generator;
import com.fss.warrini.dto.ResumeDto;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Override
    public byte[] generateTemplate1(ResumeDto resumeDto) {
        return Template1Generator.generate(resumeDto);
    }
}
