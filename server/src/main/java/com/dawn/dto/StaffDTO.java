package com.dawn.dto;

import lombok.*;

public class StaffDTO {

    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetStaff {
        private int staffId;
        private int storeId;
        private String name;
        private String position;
        private String birthDate;
        private boolean sex;
        private String wagePerHour;
        private String profileImageURL;

        public GetStaff(int storeId, String name, String position, String toString, Boolean sex, String wagePerHour, String s) {
        }
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
        private String wagePerHour;
    }
}
