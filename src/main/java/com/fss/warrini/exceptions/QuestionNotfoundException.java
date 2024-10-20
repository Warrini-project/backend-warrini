package com.fss.warrini.exceptions;

public class QuestionNotfoundException extends RuntimeException{
    public QuestionNotfoundException(){
        super("Question not found");
    }
}
