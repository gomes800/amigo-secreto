package com.gom.amigo_secreto.exception;

public class DrawNotFoundException extends RuntimeException {
    public DrawNotFoundException(Long id) {
        super("Draw not found with id: " + id);
    }
}
