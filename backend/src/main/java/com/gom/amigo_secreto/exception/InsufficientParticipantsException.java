package com.gom.amigo_secreto.exception;

public class InsufficientParticipantsException extends RuntimeException {
    public InsufficientParticipantsException() {
        super("The group needs a minimum of 3 participants.");
    }
}
