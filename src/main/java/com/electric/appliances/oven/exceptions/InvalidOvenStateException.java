package com.electric.appliances.oven.exceptions;

public class InvalidOvenStateException extends RuntimeException{
    public InvalidOvenStateException(String message){
        super(message);

    }
}
