package com.evan.blog.exception;

public class BlogException extends RuntimeException {

    public BlogException() {
        super("BlogException happened in the runtime of Blog application!");
    }

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogException(Throwable cause) {
        super(cause);
    }

}
