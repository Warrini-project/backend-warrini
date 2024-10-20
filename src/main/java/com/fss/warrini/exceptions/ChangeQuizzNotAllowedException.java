package com.fss.warrini.exceptions;

public class ChangeQuizzNotAllowedException extends RuntimeException{
    public ChangeQuizzNotAllowedException(){
        super("Changing quizz is not allowed");
    }
}
