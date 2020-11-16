package com.dawn.repository.staff;

import com.dawn.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer>, CustomStaffRepository {
}
