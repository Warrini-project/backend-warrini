package com.fss.warrini.mappers;

import com.fss.warrini.dto.UserDto;
import com.fss.warrini.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "faculty.id", target = "facultyId")
    UserDto toDto(UserEntity userEntity);

    @Mapping(source = "facultyId", target = "faculty.id")
    UserEntity toEntity(UserDto userDto);
}
