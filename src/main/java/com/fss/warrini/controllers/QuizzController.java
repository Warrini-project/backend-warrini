package com.fss.warrini.controllers;


import com.fss.warrini.dto.quizz_dtos.ChoiceDto;
import com.fss.warrini.dto.quizz_dtos.QuestionDto;
import com.fss.warrini.dto.quizz_dtos.QuizzDto;
import com.fss.warrini.services.quizz_services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizz")
public class QuizzController {

    @Autowired
    private QuizzService quizzService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizzDto quizzDto) {
        return ResponseEntity.ok().body(quizzService.createQuizz(quizzDto));
    }

    @PostMapping("/add_question")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok().body(quizzService.addQuestion(questionDto));
    }
    @PostMapping("/add_choice")
    public ResponseEntity<?> addChoice(@RequestBody ChoiceDto choiceDto) {
        return ResponseEntity.ok().body(quizzService.addChoice(choiceDto));
    }

    @DeleteMapping("/delete_choice")
    public ResponseEntity<?> deleteChoice(@RequestParam Long questionId, @RequestParam Long choiceId) {
        return ResponseEntity.ok().body(quizzService.deleteChoice(questionId, choiceId));
    }

    @DeleteMapping("/delete_question")
    public ResponseEntity<?> deleteQuestion(@RequestParam Long questionId) {
        return ResponseEntity.ok().body(quizzService.deleteQuestion(questionId));
    }

    @PutMapping("/update_question")
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok().body(quizzService.updateQuestion(questionDto));
    }

    @PutMapping("/update_choice")
    public ResponseEntity<?> updateChoice(@RequestBody ChoiceDto choiceDto) {
        return ResponseEntity.ok().body(quizzService.updateChoice(choiceDto));
    }

    @PutMapping("/update_quizz")
    public ResponseEntity<?> updateQuizz(@RequestBody QuizzDto quizzDto) {
        return ResponseEntity.ok().body(quizzService.updateQuizz(quizzDto));
    }

    @GetMapping("/get_quizz_by_skill")
    public ResponseEntity<?> getQuizzBySkill(@RequestParam String skill){
        return ResponseEntity.ok(quizzService.getQuizzBySkill(skill));
    }
}
