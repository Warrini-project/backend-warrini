package com.fss.warrini.services;


import com.fss.warrini.dto.FacultyDto;
import com.fss.warrini.entities.FacultyEntity;
import com.fss.warrini.mappers.FacultyMapper;
import com.fss.warrini.repositories.FacultyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepo facultyRepo;
    @Autowired
    private FacultyMapper facultyMapper;

    @Override
    public FacultyDto addFaculty(FacultyDto faculty) {
        FacultyEntity facultyEntity = facultyRepo.save(facultyMapper.toEntity(faculty));
        return facultyMapper.toDto(facultyEntity);
    }
}
