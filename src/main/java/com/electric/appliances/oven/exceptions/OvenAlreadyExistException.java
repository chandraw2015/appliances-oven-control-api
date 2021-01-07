package com.electric.appliances.oven.exceptions;

public class OvenAlreadyExistException extends  RuntimeException{
    public OvenAlreadyExistException(String message){
        super(message);
    }
}
