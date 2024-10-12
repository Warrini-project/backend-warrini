package com.fss.warrini.exceptions;

public class UserNameAlreadyUsedException extends RuntimeException{
    public UserNameAlreadyUsedException(String msg){
        super(msg);
    }
}
