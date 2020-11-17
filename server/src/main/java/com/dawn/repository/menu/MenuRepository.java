package com.dawn.repository.menu;

import com.dawn.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer>, CustomMenuRepository {

    public Menu findByMenuId(int menuId);
    public List<Menu> findMenuByStoreId(int storeId);
}
