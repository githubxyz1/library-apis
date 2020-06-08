package com.jm.libraryapis.libraryapis.exception;

public class LibraryResourceBadRequestException extends Exception {

    private String traceId;

    public LibraryResourceBadRequestException(String message) {
        super(message);
    }

    public LibraryResourceBadRequestException(String traceId, String s) {
        super(s);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
