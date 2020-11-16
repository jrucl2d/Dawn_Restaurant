package com.dawn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class StoreDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Create {
        private int ownerUserId;
        private String storeTitle;
        private String location;
        private String businessHour;
        private String description;
    }
}
