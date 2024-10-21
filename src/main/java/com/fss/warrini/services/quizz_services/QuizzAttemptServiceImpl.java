package com.fss.warrini.services.quizz_services;

import com.fss.warrini.dto.quizz_dtos.QuizzAttemptDto;
import com.fss.warrini.entities.UserEntity;
import com.fss.warrini.entities.quizz_entities.*;
import com.fss.warrini.exceptions.*;
import com.fss.warrini.mappers.quizz_mappers.ChoiceResponseMapper;
import com.fss.warrini.mappers.quizz_mappers.QuestionResponseMapper;
import com.fss.warrini.mappers.quizz_mappers.QuizzAttemptMapper;
import com.fss.warrini.repositories.UserRepo;
import com.fss.warrini.repositories.quizz_repositories.ChoiceRepo;
import com.fss.warrini.repositories.quizz_repositories.QuestionRepo;
import com.fss.warrini.repositories.quizz_repositories.QuizzAttemptRepo;
import com.fss.warrini.repositories.quizz_repositories.QuizzRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class QuizzAttemptServiceImpl implements QuizzAttemptService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuizzRepo quizzRepo;
    @Autowired
    private QuizzAttemptMapper quizzAttemptMapper;
    @Autowired
    private QuestionResponseMapper questionResponseMapper;
    @Autowired
    private ChoiceResponseMapper choiceResponseMapper;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private ChoiceRepo choiceRepo;

    @Autowired
    private QuizzAttemptRepo quizzAttemptRepo;

    @Override
    @Transactional
    public QuizzAttemptDto addQuizzAttempt(QuizzAttemptDto quizzAttemptDto) {
        UserEntity user = userRepo.findById(quizzAttemptDto.getUserId())
                .orElseThrow(UserNotFoundException::new);
        QuizzEntity quizz = quizzRepo.findById(quizzAttemptDto.getQuizzId())
                .orElseThrow(QuizzNotfoundException::new);

        if (quizzAttemptDto.getQuestionsResponses() == null || quizzAttemptDto.getQuestionsResponses().isEmpty()) {
            throw new QuizzResponsesEmptyException();
        }

        QuizzAttemptEntity quizzAttemptEntity = quizzAttemptMapper.toEntity(quizzAttemptDto);
        quizzAttemptEntity.setUser(user);
        quizzAttemptEntity.setQuizz(quizz);

        List<QuestionResponseEntity> questionResponses = quizzAttemptDto.getQuestionsResponses()
                .stream()
                .map(questionResponseDto -> {
                    QuestionEntity question = questionRepo.findById(questionResponseDto.getQuestionId())
                            .orElseThrow(QuestionNotfoundException::new);

                    QuestionResponseEntity questionResponseEntity = questionResponseMapper.toEntity(questionResponseDto);
                    questionResponseEntity.setQuestion(question);
                    questionResponseEntity.setQuizzAttempt(quizzAttemptEntity);

                    AtomicBoolean responseCorrect = new AtomicBoolean(true);
                    List<ChoiceResponseEntity> choicesResponses = questionResponseDto.getChoicesResponses()
                            .stream()
                            .map(choiceResponseDto -> {
                                ChoiceEntity choiceEntity = choiceRepo.findById(choiceResponseDto.getChoiceId())
                                        .orElseThrow(ChoiceNotfoundException::new);

                                ChoiceResponseEntity choiceResponseEntity = choiceResponseMapper.toEntity(choiceResponseDto);
                                choiceResponseEntity.setChoice(choiceEntity);
                                choiceResponseEntity.setQuestionResponse(questionResponseEntity);

                                // Update the correctness of the entire question response.
                                if (responseCorrect.get() && choiceEntity.isCorrect() != choiceResponseDto.getUserValue()) {
                                    responseCorrect.set(false);
                                }

                                choiceResponseEntity.setUserChoiceCorrect(choiceEntity.isCorrect() == choiceResponseDto.getUserValue());
                                return choiceResponseEntity;
                            })
                            .collect(Collectors.toList());

                    questionResponseEntity.setChoicesResponses(choicesResponses);
                    questionResponseEntity.setResponseCorrect(responseCorrect.get());
                    quizzAttemptEntity.setScore(quizzAttemptEntity.getScore() + (responseCorrect.get() ? 1 : 0));
                    return questionResponseEntity;
                })
                .collect(Collectors.toList());

        quizzAttemptEntity.setQuestionsResponses(questionResponses);

        QuizzAttemptEntity savedEntity = quizzAttemptRepo.save(quizzAttemptEntity);

        return quizzAttemptMapper.toDto(savedEntity);
    }

    @Override
    public List<QuizzAttemptDto> getQuizzAttemptByUserAndQuizz(Long quizzId, Long userId) {
        return List.of();
    }
}
