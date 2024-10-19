package com.fss.warrini.services;

import com.fss.warrini.PdfGenerators.Template1Generator;
import com.fss.warrini.PdfGenerators.Template2Generator;
import com.fss.warrini.dto.ResumeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResourceLoader resourceLoader;


    @Override
    public byte[] generateTemplate1(ResumeDto resumeDto) {
        return Template1Generator.generate(resumeDto);
    }

    @Override
    public byte[] generateTemplate2(ResumeDto resumeDto, String profileImage) {
        return Template2Generator.generate(resumeDto, profileImage, resourceLoader);
    }
}
