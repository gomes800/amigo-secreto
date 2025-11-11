package com.gom.amigo_secreto.exception;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(Long id) {
        super("Group not found with id: " + id);
    }
}
