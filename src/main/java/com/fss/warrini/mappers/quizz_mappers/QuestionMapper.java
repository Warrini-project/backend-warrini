package com.fss.warrini.mappers.quizz_mappers;


import com.fss.warrini.dto.quizz_dtos.ChoiceDto;
import com.fss.warrini.dto.quizz_dtos.QuestionDto;
import com.fss.warrini.dto.quizz_dtos.QuizzDto;
import com.fss.warrini.entities.quizz_entities.ChoiceEntity;
import com.fss.warrini.entities.quizz_entities.QuestionEntity;
import com.fss.warrini.entities.quizz_entities.QuizzEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = ChoiceMapper.class)
public interface QuestionMapper {

    @Mapping(source = "quizz.id", target = "quizzId")
    @Mapping(target = "choices", expression = "java(mapChoices(questionEntity.getChoices(), questionEntity.getQuizz().getId()))")
    QuestionDto toDto(QuestionEntity questionEntity);

    @Mapping(source = "quizzId", target = "quizz.id")
    QuestionEntity toEntity(QuestionDto dto);

    default List<ChoiceDto> mapChoices(List<ChoiceEntity> choiceEntities, Long questionId) {
        return choiceEntities != null ? choiceEntities.stream()
                .map(choiceEntity -> {
                    return Mappers.getMapper(ChoiceMapper.class).toDtoWithQuestionId(choiceEntity, questionId);
                })
                .collect(Collectors.toList()): new ArrayList<>();
    }
}
