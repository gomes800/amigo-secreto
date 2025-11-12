package com.gom.amigo_secreto.exception;

public class DrawAlreadyCompletedException extends RuntimeException {
    public DrawAlreadyCompletedException() {
        super("Draw already completed for this group.");
    }
}
