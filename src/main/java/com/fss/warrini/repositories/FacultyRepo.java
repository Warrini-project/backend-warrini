package com.fss.warrini.repositories;

import com.fss.warrini.entities.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepo extends JpaRepository<FacultyEntity, Long> {
}
