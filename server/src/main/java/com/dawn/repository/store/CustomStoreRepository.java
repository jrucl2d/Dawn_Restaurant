package com.dawn.repository.store;

import com.dawn.model.Store;

import java.util.List;

public interface CustomStoreRepository {

    public List<Store> getAllStoreOfUserByUserId(int userId);
    public void deleteAllStoreOfUserByUserId(int userId);
}
