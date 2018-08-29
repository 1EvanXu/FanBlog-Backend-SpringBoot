package com.evan.blog.exception;

public class ResourceNotExistException extends BlogException {
    public ResourceNotExistException() {
        super("The resource you request not exisit.");
    }

    public ResourceNotExistException(String message) {
        super(message);
    }

    public ResourceNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotExistException(Throwable cause) {
        super(cause);
    }
}
