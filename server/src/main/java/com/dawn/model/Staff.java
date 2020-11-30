package com.dawn.model;

import com.dawn.common.CloudConstants;
import com.dawn.dto.StaffDTO;
import com.dawn.exception.DawnException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Staff updateStaffByUpdateDTO(StaffDTO.UpdateStaff updateStaff) throws DawnException {
        this.name = updateStaff.getName();
        this.position = updateStaff.getPosition();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthDate = format.parse(updateStaff.getBirthDate());
        } catch (ParseException e) {
            throw new DawnException(
                    "잘못된 날짜형식 제공",
                    String.format("[%s]는 올바르지 않은 날짜형식입니다.", updateStaff.getBirthDate()));
        }
        this.sex = updateStaff.isSex();
        this.wagePerHour = updateStaff.getWagePerHour();
        return this;
    }
}
