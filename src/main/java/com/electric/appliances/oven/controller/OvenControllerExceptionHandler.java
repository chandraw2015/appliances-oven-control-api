package com.electric.appliances.oven.controller;



import com.electric.appliances.oven.models.ApiError;
import com.electric.appliances.oven.exceptions.OvenNotFoundException;
import com.electric.appliances.oven.exceptions.OvenNotStartedException;
import com.electric.appliances.oven.exceptions.OvenAlreadyExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * A centralized exception handler controller for all endpoints configured.
 * @author Chandra rawat
 */
@ControllerAdvice
@RestController
public class OvenControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(OvenNotStartedException.class)
    public final ResponseEntity<ApiError> handleOvenNotStartedException(OvenNotStartedException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(OvenAlreadyExistException.class)
    public final ResponseEntity<ApiError> handleOvenAlreadyExistException(OvenAlreadyExistException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(OvenNotFoundException.class)
    public final ResponseEntity<ApiError> handleOvenNotFoundException(OvenNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleAllExceptions(Exception ex, WebRequest request) {
        ApiError errorDetails = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ApiError>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ApiError errorDetails = new ApiError(new Date(), "Validation Failed", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
