package com.fss.warrini.services.quizz_services;

import com.fss.warrini.dto.quizz_dtos.ChoiceDto;
import com.fss.warrini.dto.quizz_dtos.QuestionDto;
import com.fss.warrini.dto.quizz_dtos.QuizzDto;
import com.fss.warrini.entities.quizz_entities.ChoiceEntity;
import com.fss.warrini.entities.quizz_entities.QuestionEntity;
import com.fss.warrini.entities.quizz_entities.QuizzEntity;
import com.fss.warrini.exceptions.*;
import com.fss.warrini.mappers.quizz_mappers.ChoiceMapper;
import com.fss.warrini.mappers.quizz_mappers.QuestionMapper;
import com.fss.warrini.mappers.quizz_mappers.QuizzMapper;
import com.fss.warrini.repositories.quizz_repositories.ChoiceRepo;
import com.fss.warrini.repositories.quizz_repositories.QuestionRepo;
import com.fss.warrini.repositories.quizz_repositories.QuizzRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.function.Consumer;

@Service
public class QuizzServiceImpl implements QuizzService {

    private QuizzRepo quizzRepo;
    private QuestionRepo questionRepo;
    private ChoiceRepo choiceRepo;
    private QuizzMapper quizzMapper;
    private QuestionMapper questionMapper;
    private ChoiceMapper choiceMapper;

    @Autowired
    public QuizzServiceImpl(QuizzRepo quizzRepo, QuestionRepo questionRepo, ChoiceRepo choiceRepo, QuizzMapper quizzMapper, QuestionMapper questionMapper, ChoiceMapper choiceMapper){
        this.quizzRepo = quizzRepo;
        this.questionRepo = questionRepo;
        this.choiceRepo = choiceRepo;
        this.quizzMapper = quizzMapper;
        this.questionMapper = questionMapper;
        this.choiceMapper = choiceMapper;
    }


    @Override
    public QuizzDto createQuizz(QuizzDto quizzDto) {
        QuizzEntity quizzEntity = quizzRepo.save(quizzMapper.toEntity(quizzDto));
        return quizzMapper.toDto(quizzEntity);
    }

    @Override
    public QuizzDto updateQuizz(QuizzDto quizzDto){
        QuizzEntity quizz = quizzRepo.findById(quizzDto.getId()).orElseThrow(() -> new QuizzNotfoundException());
        if(quizzDto.getQuestions() != null) {
            throw new UpdateQuestionNotAllowedException();
        }
        updateIfNotNull(quizzDto.getDomain(), quizz::setDomain);
        updateIfNotNull(quizzDto.getDescription(), quizz::setDescription);
        updateIfNotNull(quizzDto.getLevel(), quizz::setLevel);
        updateIfNotNull(quizzDto.getImage(), quizz::setImage);
        updateIfNotNull(quizzDto.getTitle(), quizz::setTitle);


        QuizzEntity quizzEntity = quizzRepo.save(quizz);
        QuizzDto savedQuizzDto = quizzMapper.toDto(quizzEntity);
        return savedQuizzDto;
    }

    @Override
    @Transactional
    public QuestionDto addQuestion(QuestionDto questionDto){
        QuizzEntity quizz = quizzRepo.findById(questionDto.getQuizzId()).orElseThrow(() -> new QuizzNotfoundException());
        QuestionEntity questionEntity = questionMapper.toEntity(questionDto);
        questionEntity.setQuizz(quizz);
        quizz.getQuestions().add(questionEntity);
        questionEntity.getChoices().forEach(choiceEntity -> {choiceEntity.setQuestion(questionEntity);});
        QuestionEntity savedQuestion = questionRepo.save(questionEntity);

        QuestionDto savedQuestionDto = questionMapper.toDto(savedQuestion);
        return savedQuestionDto;
    }

    @Override
    @Transactional
    public String deleteQuestion(Long questionId){
        QuestionEntity questionEntity = questionRepo.findById(questionId).orElseThrow(() -> new QuestionNotfoundException());
        QuizzEntity quizz = questionEntity.getQuizz();
        quizz.getQuestions().remove(questionEntity);
        quizzRepo.save(quizz);
        return "Quizz deleted";
    }

    @Override
    public QuestionDto updateQuestion(QuestionDto questionDto){
        QuestionEntity question = questionRepo.findById(questionDto.getId()).orElseThrow(() -> new QuestionNotfoundException());
        QuizzEntity quizz = question.getQuizz();
        if (!Objects.equals(questionDto.getQuizzId(), quizz.getId())) {
            throw new ChangeQuizzNotAllowedException();
        }
        if(questionDto.getChoices() != null) {
            throw new UpdateChoicesNotAllowedException();
        }
        question.setSentence(questionDto.getSentence());
        QuestionEntity questionEntity = questionRepo.save(question);
        QuestionDto savedQuestionDto = questionMapper.toDto(questionEntity);
        return savedQuestionDto;
    }

    @Override
    @Transactional
    public ChoiceDto addChoice(ChoiceDto choiceDto){
        QuestionEntity __ = questionRepo.findById(choiceDto.getQuestionId())
                .orElseThrow(() -> new QuestionNotfoundException());

        ChoiceEntity choiceEntity = choiceMapper.toEntity(choiceDto);
        ChoiceEntity savedChoice = choiceRepo.save(choiceEntity);
        ChoiceDto savedChoiceDto = choiceMapper.toDto(savedChoice);
        return savedChoiceDto;

    }

    @Override
    @Transactional
    public String deleteChoice(Long questionId, Long choiceId) {
        QuestionEntity question = questionRepo.findById(questionId)
                .orElseThrow(() -> new QuestionNotfoundException());

        ChoiceEntity choiceEntity = choiceRepo.findById(choiceId).orElseThrow(() -> new ChoiceNotfoundException());
        question.getChoices().remove(choiceEntity);
        questionRepo.save(question);
        return "Choice deleted";
    }

    @Override
    @Transactional
    public ChoiceDto updateChoice(ChoiceDto choiceDto)  {
        ChoiceEntity choice = choiceRepo.findById(choiceDto.getId()).orElseThrow(() -> new ChoiceNotfoundException());
        QuestionEntity question = choice.getQuestion();

        if (!question.getId().equals(choiceDto.getQuestionId())) {
            throw new ChangeQuestionNotAllowedException();
        }
        ChoiceEntity choiceEntity = choiceRepo.save(choiceMapper.toEntity(choiceDto));
        ChoiceDto savedChoiceDto = choiceMapper.toDto(choiceEntity);
        return savedChoiceDto;
    }



    private void updateIfNotNull(String value, Consumer<String> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
