package com.fss.warrini.repositories.quizz_repositories;

import com.fss.warrini.entities.quizz_entities.QuizzEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzRepo extends JpaRepository<QuizzEntity, Long> {
}
