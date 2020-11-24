package com.dawn.repository.staff;

import com.dawn.model.Staff;

import java.util.List;

public interface CustomStaffRepository {

    public List<Staff> findByStoreByStoreId(int storeId);
}
