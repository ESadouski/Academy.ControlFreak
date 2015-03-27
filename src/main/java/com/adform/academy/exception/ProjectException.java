package com.adform.academy.exception;

public class ProjectException extends Exception {

    private Exception hiddenException;

    public ProjectException(String message) {
        super(message);
    }

    public ProjectException(String message, Exception e) {
        super(message);
        this.hiddenException = e;
    }

}
