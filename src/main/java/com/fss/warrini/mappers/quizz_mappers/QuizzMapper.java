package com.fss.warrini.mappers.quizz_mappers;

import com.fss.warrini.dto.quizz_dtos.QuestionDto;
import com.fss.warrini.dto.quizz_dtos.QuizzDto;
import com.fss.warrini.entities.quizz_entities.QuestionEntity;
import com.fss.warrini.entities.quizz_entities.QuizzEntity;
import com.fss.warrini.mappers.quizz_mappers.QuestionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = QuestionMapper.class)
public interface QuizzMapper {

    @Mapping(target = "questions", expression = "java(mapQuestions(quizzEntity.getQuestions()))")
    QuizzDto toDto(QuizzEntity quizzEntity);

    QuizzEntity toEntity(QuizzDto quizzDto);

    default List<QuestionDto> mapQuestions(List<QuestionEntity> questionEntities) {
        try{return questionEntities.stream()
                .map(questionEntity -> Mappers.getMapper(QuestionMapper.class).toDto(questionEntity))
                .collect(Collectors.toList());}catch (NullPointerException e){
            return new ArrayList<>();
            // in case when the admin want to add quizz without questions at beginning
        }
    }
}
