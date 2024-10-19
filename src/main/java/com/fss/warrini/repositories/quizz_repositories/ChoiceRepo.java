package com.fss.warrini.repositories.quizz_repositories;

import com.fss.warrini.entities.quizz_entities.ChoiceEntity;
import com.fss.warrini.entities.quizz_entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepo extends JpaRepository<ChoiceEntity, Long> {
}
