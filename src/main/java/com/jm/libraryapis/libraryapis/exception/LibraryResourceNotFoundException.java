package com.jm.libraryapis.libraryapis.exception;

public class LibraryResourceNotFoundException extends Exception {

    private String traceId;

    public LibraryResourceNotFoundException(String message) {
        super(message);
    }

    public LibraryResourceNotFoundException(String traceId, String s) {
        super(s);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
