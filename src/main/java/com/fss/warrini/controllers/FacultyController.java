package com.fss.warrini.controllers;


import com.fss.warrini.dto.FacultyDto;
import com.fss.warrini.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @PostMapping("/add")
    public ResponseEntity<FacultyDto> addFaculty(@RequestBody FacultyDto facultyDto) {
        return ResponseEntity.ok(facultyService.addFaculty(facultyDto));
    }
}
