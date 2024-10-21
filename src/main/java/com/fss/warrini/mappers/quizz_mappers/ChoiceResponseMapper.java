package com.fss.warrini.mappers.quizz_mappers;


import com.fss.warrini.dto.quizz_dtos.ChoiceResponseDto;
import com.fss.warrini.entities.quizz_entities.ChoiceResponseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChoiceResponseMapper {

    ChoiceResponseMapper INSTANCE = Mappers.getMapper(ChoiceResponseMapper.class);

    // Convert ChoiceResponseEntity to ChoiceResponseDto
    @Mapping(source = "choice.id", target = "choiceId")
    @Mapping(source = "questionResponse.id", target = "questionResponseId")
    ChoiceResponseDto toDto(ChoiceResponseEntity entity);

    // Convert ChoiceResponseDto to ChoiceResponseEntity
    @Mapping(source = "choiceId", target = "choice.id")
    ChoiceResponseEntity toEntity(ChoiceResponseDto dto);
}
