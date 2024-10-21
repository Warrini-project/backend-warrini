package com.fss.warrini.controllers;


import com.fss.warrini.dto.quizz_dtos.QuizzAttemptDto;
import com.fss.warrini.services.quizz_services.QuizzAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizz_attempt")
public class QuizzAttemptController {
    @Autowired
    private QuizzAttemptService quizzAttemptService;

    @PostMapping("/add")
    public ResponseEntity<?> addQuizzAttempt(@RequestBody QuizzAttemptDto quizzAttemptDto) {
        return ResponseEntity.ok().body(quizzAttemptService.addQuizzAttempt(quizzAttemptDto));
    }
}
