package com.dawn.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DawnErrorType {
    INVALID_PATH_PARAMETER("C1", "INVALID_PATH_PARAMETER"),
    INVALID_QUERY_PARAMETER("C2", "INVALID_QUERY_PARAMETER"),
    INVALID_REQUEST_BODY("C3", "INVALID_PATH_PARAMETER");

    String errorCode;
    String errorTitle;

}
