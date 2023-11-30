package org.massonus.exception;

public class IndexNotFoundException extends RuntimeException {

    public IndexNotFoundException(int index) {
        super("no such index exist " + index);
    }
}
