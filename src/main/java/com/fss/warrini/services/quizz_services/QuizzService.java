package com.fss.warrini.services.quizz_services;

import com.fss.warrini.dto.quizz_dtos.ChoiceDto;
import com.fss.warrini.dto.quizz_dtos.QuestionDto;
import com.fss.warrini.dto.quizz_dtos.QuizzDto;
import com.fss.warrini.exceptions.*;

public interface QuizzService {
    QuizzDto createQuizz(QuizzDto quizzDto);

    QuizzDto updateQuizz(QuizzDto quizzDto);
    QuestionDto addQuestion(QuestionDto questionDto);
    String deleteQuestion(Long questionId);
    QuestionDto updateQuestion(QuestionDto questionDto);
    ChoiceDto addChoice(ChoiceDto choiceDto);
    String deleteChoice(Long questionId, Long choiceId);
    ChoiceDto updateChoice(ChoiceDto choiceDto);
}
