package com.dawn.common;

import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
@Setter
public class DawnCodingError {

    private int errorCode;
    private String message;
    private String detail;
    private String stackTrace;

    public DawnCodingError(int errorCode, String message, String detail) {
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
    }

    public DawnCodingError(int errorCode, String message, String detail, Exception exception) {
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        this.stackTrace = stringWriter.toString();
    }
}
