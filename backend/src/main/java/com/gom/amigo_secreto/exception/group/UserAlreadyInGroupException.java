package com.gom.amigo_secreto.exception.group;

public class UserAlreadyInGroupException extends RuntimeException {
    public UserAlreadyInGroupException(Long userId, Long groupId) {
        super("User id " + userId + " already in group id " + groupId);
    }
}
