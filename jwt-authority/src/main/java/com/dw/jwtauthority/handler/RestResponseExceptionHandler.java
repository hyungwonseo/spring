package com.dw.jwtauthority.handler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dw.jwtauthority.dto.ErrorDto;
import com.dw.jwtauthority.exception.DuplicateMemberException;
import com.dw.jwtauthority.exception.NotFoundMemberException;
import com.dw.jwtauthority.exception.UserNotFoundException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(value = { DuplicateMemberException.class })
    @ResponseBody
    protected ErrorDto conflict(RuntimeException ex, WebRequest request) {
        return new ErrorDto(CONFLICT.value(), ex.getMessage());
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(value = { NotFoundMemberException.class, AccessDeniedException.class })
    @ResponseBody
    protected ErrorDto forbidden(RuntimeException ex, WebRequest request) {
        return new ErrorDto(FORBIDDEN.value(), ex.getMessage());
    }
    
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(value = { UserNotFoundException.class, RuntimeException.class })
    @ResponseBody
    protected ErrorDto unauthorized(RuntimeException ex, WebRequest request) {
        return new ErrorDto(UNAUTHORIZED.value(), ex.getMessage());
    }
}