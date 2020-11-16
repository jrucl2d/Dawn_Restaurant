package com.dawn.repository.menu;

import com.dawn.model.Menu;

import java.util.List;

public interface CustomMenuRepository {

    public List<Menu> findByStoreId(int storeId);
}
