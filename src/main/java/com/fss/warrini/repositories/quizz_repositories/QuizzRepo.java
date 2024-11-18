package com.fss.warrini.repositories.quizz_repositories;

import com.fss.warrini.entities.quizz_entities.QuizzEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizzRepo extends JpaRepository<QuizzEntity, Long> {

    Optional<List<QuizzEntity>> findBySkill(String skill);
}
