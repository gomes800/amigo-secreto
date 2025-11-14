package com.gom.amigo_secreto.exception;

import com.gom.amigo_secreto.exception.draw.DrawAlreadyCompletedException;
import com.gom.amigo_secreto.exception.draw.DrawNotFoundException;
import com.gom.amigo_secreto.exception.draw.InsufficientParticipantsException;
import com.gom.amigo_secreto.exception.group.GroupNotFoundException;
import com.gom.amigo_secreto.exception.group.UserAlreadyInGroupException;
import com.gom.amigo_secreto.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Not Found (404)
    @ExceptionHandler({
            UserNotFoundException.class,
            GroupNotFoundException.class,
            DrawNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Conflict (409)
    @ExceptionHandler({
            UserAlreadyInGroupException.class,
            DrawAlreadyCompletedException.class
    })
    public ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // Bad Request (400)
    @ExceptionHandler(InsufficientParticipantsException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // Internal Server Error (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred: " + ex.getMessage()
        );
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse error = new ErrorResponse(
                status.value(),
                message,
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(error);
    }
}