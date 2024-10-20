package com.fss.warrini.exceptions;

public class UpdateChoicesNotAllowedException extends RuntimeException{
    public UpdateChoicesNotAllowedException(){
        super("Updating choices here is not allowed, please use the dedicated endpoint [/api/quizz/update_choice]");
    }
}
