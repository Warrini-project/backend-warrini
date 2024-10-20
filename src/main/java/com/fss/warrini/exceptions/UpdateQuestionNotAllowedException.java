package com.fss.warrini.exceptions;

public class UpdateQuestionNotAllowedException extends RuntimeException{
    public UpdateQuestionNotAllowedException(){
        super("Updating questions here is not allowed, please use the dedicated endpoint [/api/quizz/update_question]");
    }
}
