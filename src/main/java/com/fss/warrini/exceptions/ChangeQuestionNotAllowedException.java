package com.fss.warrini.exceptions;

public class ChangeQuestionNotAllowedException extends RuntimeException{
    public ChangeQuestionNotAllowedException(){
        super("Changing question is not allowed");
    }
}
