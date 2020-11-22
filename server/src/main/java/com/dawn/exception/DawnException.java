package com.dawn.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DawnException extends Exception {

    private String message;
    private String detail;

    public DawnException(String message, String detail) {
        super();
        this.message = message;
        this.detail = detail;
        /*
        StringWriter sw = new StringWriter();
        printStackTrace(new PrintWriter(sw));
        this.stackTrace = sw.toString();
        */
    }
}
