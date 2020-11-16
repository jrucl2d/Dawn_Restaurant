package com.dawn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    NEW(0, "새 주문"),
    COOKING(1, "조리 중"),
    COOKING_COMPLETE(2, "조리 완료"),
    SERVED(3, "음식 수령");

    private int statusCode;
    private String statusTitle;

}
