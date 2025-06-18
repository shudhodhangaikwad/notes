package com.jsiders.notes.exceptions;

import com.jsiders.notes.dto.ErrorResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        ErrorResponseDto errorResponseDto = getErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors);
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = List.of(e.getMessage());
        ErrorResponseDto errorResponseDto = getErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors);
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException e) {
        ErrorResponseDto errorResponseDto = getErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                List.of(e.getMessage()));
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAllExceptions(Exception e) {
        ErrorResponseDto errorResponseDto = getErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                List.of(e.getMessage()));
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDto);
    }

    private ErrorResponseDto getErrorResponseDto(int statusCode, String message, List<String> errors) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setStatusCode(statusCode);
        errorResponseDto.setMessage(message);
        errorResponseDto.setErrors(errors);
        return errorResponseDto;
    }


}
