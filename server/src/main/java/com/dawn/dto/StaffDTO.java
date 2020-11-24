package com.dawn.dto;

import lombok.*;

public class StaffDTO {

    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetStaff {
        private int storeId;
        private String name;
        private String position;
        private String birthDate;
        private boolean sex;
        private int wagePerHour;
        private String profileImageURL;
    }

    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateStaff {
        private int storeId;
        private String name;
        private String position;
        private String birthDate;
        private boolean sex;
        private int wagePerHour;
    }
}
