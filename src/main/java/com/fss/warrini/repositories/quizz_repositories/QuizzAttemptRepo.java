package com.fss.warrini.repositories.quizz_repositories;

import com.fss.warrini.entities.quizz_entities.QuizzAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzAttemptRepo extends JpaRepository<QuizzAttemptEntity, Long> {
}
