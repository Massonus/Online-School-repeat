package org.massonus.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(int id) {
        super("nu such id exist " + id);
    }
}
