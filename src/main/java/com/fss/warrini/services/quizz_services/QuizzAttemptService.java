package com.fss.warrini.services.quizz_services;

import com.fss.warrini.dto.quizz_dtos.QuizzAttemptDto;

import java.util.List;

public interface QuizzAttemptService {

    QuizzAttemptDto addQuizzAttempt(QuizzAttemptDto quizzAttemptDto);
    List<QuizzAttemptDto> getQuizzAttemptByUserAndQuizz(Long quizzId, Long userId);
}
