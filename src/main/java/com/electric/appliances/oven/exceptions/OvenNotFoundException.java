package com.electric.appliances.oven.exceptions;

public class OvenNotFoundException extends RuntimeException{
    public OvenNotFoundException(String message){
        super(message);
    }
}
