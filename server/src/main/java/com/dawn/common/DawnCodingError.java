package com.dawn.common;

import com.dawn.exception.DawnException;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
@Setter
public class DawnCodingError {

    private String errorCode;
    private String message;
    private String detail;
    private String stackTrace;

    public DawnCodingError(DawnException e) {
        this.errorCode = "E0";
        this.message = e.getMessage();
        this.detail = e.getDetail();
        this.stackTrace = e.getStackTrace().toString();
    }

    public DawnCodingError(DawnErrorType dawnError, String detail) {
        this.errorCode = dawnError.getErrorCode() ;
        this.message = dawnError.getErrorTitle();
        this.detail = detail;
    }

    public DawnCodingError(String errorCode, String message, String detail) {
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
    }

    public DawnCodingError(String errorCode, String message, String detail, Exception exception) {
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        this.stackTrace = stringWriter.toString();
    }
}
