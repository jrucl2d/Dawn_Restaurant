package com.dawn.model;

import com.dawn.common.CloudConstants;
import com.dawn.dto.StaffDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int staffId;

    @ManyToOne
    private Store store;

    @Column
    private String name;

    @Column
    private String position;

    @Column
    private Date birthDate;

    @Column
    private Boolean sex;

    @Column
    private String wagePerHour;

    @Column
    private String profileImageName;

    public Staff(Store store, String name, String position, Date birthDate,
                 Boolean sex, String wagePerHour) {
        this.store = store;
        this.name = name;
        this.position = position;
        this.birthDate = birthDate;
        this.sex = sex;
        this.wagePerHour = wagePerHour;
    }

    public static StaffDTO.GetStaff toGetStaff(Staff staff) {
        return new StaffDTO.GetStaff(
                staff.getStaffId(), staff.getStore().getStoreId(), staff.getName(), staff.getPosition(),
                staff.getBirthDate().toString(), staff.getSex(), staff.getWagePerHour(),
                CloudConstants.CloudStorageBaseURL + "/" + staff.getProfileImageName());
    }
}
