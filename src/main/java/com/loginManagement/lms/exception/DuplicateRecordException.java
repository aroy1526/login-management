package com.loginManagement.lms.exception;

public class DuplicateRecordException extends RuntimeException{

    public DuplicateRecordException(String msg){
        super(msg);
    }
}
