package com.electric.appliances.oven.exceptions.exception;

public class OvenNotFoundException extends RuntimeException{
    public OvenNotFoundException(String message){
        super(message);
    }
}
