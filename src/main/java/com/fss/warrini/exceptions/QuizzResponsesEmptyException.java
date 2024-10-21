package com.fss.warrini.exceptions;

public class QuizzResponsesEmptyException extends RuntimeException{
    public QuizzResponsesEmptyException(){
        super("Quizz responses cannot be empty");
    }
}
