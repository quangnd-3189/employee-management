package com.learnjiava.employee_management;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.learnjiava.employee_management.common.httpresponse.BaseResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleValidationExceptions(
            Exception ex) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();

        if (ex instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;

            MethodArgumentNotValidException validationEx =
                    (MethodArgumentNotValidException) ex;

            message = validationEx.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
        }

        if(ex instanceof ResponseStatusException) {
          ResponseStatusException responseStatusEx = (org.springframework.web.server.ResponseStatusException) ex;
          status = HttpStatus.BAD_REQUEST;
          message = responseStatusEx.getReason();
        }

        return new ResponseEntity<>(new BaseResponse<>(null, status.value(), message), status);
    }
}
