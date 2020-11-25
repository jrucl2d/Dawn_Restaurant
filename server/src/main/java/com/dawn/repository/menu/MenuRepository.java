package com.dawn.repository.menu;

import com.dawn.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>, CustomMenuRepository {

    public Menu findByMenuId(int menuId);
}
