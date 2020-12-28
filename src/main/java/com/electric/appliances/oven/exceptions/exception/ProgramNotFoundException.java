package com.electric.appliances.oven.exceptions.exception;

public class ProgramNotFoundException extends  RuntimeException{
    public ProgramNotFoundException(String message){
        super(message);
    }
}
