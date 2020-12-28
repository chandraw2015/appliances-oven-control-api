package com.electric.appliances.oven.exceptions;

import com.electric.appliances.oven.exceptions.exception.OvenNotFoundException;
import com.electric.appliances.oven.exceptions.exception.OvenNotStartedException;
import com.electric.appliances.oven.exceptions.exception.ProgramNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class OvenCustomizedExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(OvenNotStartedException.class)
    public final ResponseEntity<ApiError> handleOvenNotStartedException(OvenNotStartedException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ProgramNotFoundException.class)
    public final ResponseEntity<ApiError> handleProgramNotFoundException(ProgramNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OvenNotFoundException.class)
    public final ResponseEntity<ApiError> handleProgramNotFoundException(OvenNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
