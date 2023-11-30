package org.massonus.exception;

public class NoSuchPersonException extends RuntimeException {

    public NoSuchPersonException() {
        super("you must create more Persons than Lectures");
    }
}
