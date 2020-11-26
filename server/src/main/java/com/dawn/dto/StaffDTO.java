package com.dawn.dto;

import lombok.*;

public class StaffDTO {

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

    }

    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateStaff {
        private int staffId;
        private String name;
        private String position;
        private String birthDate;
        private boolean sex;
        private String wagePerHour;
    }

}
