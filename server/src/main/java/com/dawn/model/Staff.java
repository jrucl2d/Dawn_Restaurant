package com.dawn.model;

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
    private int wagePerHour;

    public Staff(Store store, String name, String position, Date birthDate, Boolean sex, int wagePerHour) {
        this.store = store;
        this.name = name;
        this.position = position;
        this.birthDate = birthDate;
        this.sex = sex;
        this.wagePerHour = wagePerHour;
    }
}
