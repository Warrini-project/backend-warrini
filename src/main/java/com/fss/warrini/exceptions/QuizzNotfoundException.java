package com.fss.warrini.exceptions;

public class QuizzNotfoundException extends RuntimeException{
    public QuizzNotfoundException(){
        super("Quizz not found");
    }
}
