package com.fss.warrini.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(FacultyNotFoundException.class)
    public ResponseEntity<String> facultyNotFound(FacultyNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UserNameAlreadyUsedException.class)
    public ResponseEntity<String> userNameAlreadyUsed(UserNameAlreadyUsedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(QuizzNotfoundException.class)
    public ResponseEntity<String> quizzNotFound(QuizzNotfoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(QuestionNotfoundException.class)
    public ResponseEntity<String> questionNotFound(QuestionNotfoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ChoiceNotfoundException.class)
    public ResponseEntity<String> choiceNotFound(ChoiceNotfoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UpdateQuestionNotAllowedException.class)
    public ResponseEntity<String> updateQuestionNotAllowed(UpdateQuestionNotAllowedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UpdateChoicesNotAllowedException.class)
    public ResponseEntity<String> updateChoiceNotAllowed(UpdateChoicesNotAllowedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ChangeQuestionNotAllowedException.class)
    public ResponseEntity<String> changeQuestionNotAllowed(ChangeQuestionNotAllowedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ChangeQuizzNotAllowedException.class)
    public ResponseEntity<String> changeQuizzNotAllowed(ChangeQuizzNotAllowedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
