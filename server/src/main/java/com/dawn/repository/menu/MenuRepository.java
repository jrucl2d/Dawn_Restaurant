package com.dawn.repository.menu;

import com.dawn.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer>, CustomMenuRepository {
}
