package com.fss.warrini.controllers;


import com.fss.warrini.dto.ResumeDto;
import com.fss.warrini.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping(value = "/generate", consumes = "multipart/form-data")
    public ResponseEntity<?> generate(@RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture,
                                      @RequestParam int templateIndex,
                                      @RequestPart(value = "resumeDto") ResumeDto resumeDto){
        try {

            String base64Image = Base64.getEncoder().encodeToString(profilePicture.getBytes());
            String profileImageSrc = "data:" + profilePicture.getContentType() + ";base64," + base64Image;


            byte[] pdfBytes = templateIndex == 1 ? resumeService.generateTemplate1(resumeDto) :
            resumeService.generateTemplate2(resumeDto, profileImageSrc);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "resume.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
