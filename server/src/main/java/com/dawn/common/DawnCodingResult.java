package com.dawn.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DawnCodingResult<T> {

    private DawnCodingError error;
    private T result;

    public static DawnCodingResult<String> OK() {
        return new DawnCodingResult<>(null, "OK");
    }
}
