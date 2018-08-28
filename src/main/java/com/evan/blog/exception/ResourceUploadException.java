package com.evan.blog.exception;

public class ResourceUploadException extends BlogException {

    public ResourceUploadException() {
        super("Exception happened when uploading resource.");
    }

    public ResourceUploadException(String message) {
        super(message);
    }

    public ResourceUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceUploadException(Throwable cause) {
        super("Exception happened when uploading resource.", cause);
    }
}
