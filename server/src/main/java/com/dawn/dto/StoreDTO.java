package com.dawn.dto;

import lombok.*;

public class StoreDTO {

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetStore {
        private int storeId;
        private String storeTitle;
        private String location;
        private String businessHour;
        private String description;
        private String profileImage;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStore {
        private int ownerUserId;
        private String storeTitle;
        private String location;
        private String businessHour;
        private String description;
        private byte[] profileImage;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DeleteStore {
        private int storeId;
    }
}
