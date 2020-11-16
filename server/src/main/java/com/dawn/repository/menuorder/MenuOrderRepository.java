package com.dawn.repository.menuorder;

import com.dawn.model.MenuOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOrderRepository extends JpaRepository<MenuOrder, Integer>, CustomMenuOrderRepository {
}
