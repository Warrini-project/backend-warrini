package com.fss.warrini.mappers;

import com.fss.warrini.dto.FacultyDto;
import com.fss.warrini.dto.UserDto;
import com.fss.warrini.entities.FacultyEntity;
import com.fss.warrini.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    FacultyDto toDto(FacultyEntity facultyEntity);

    FacultyEntity toEntity(FacultyDto facultyDto);
}
