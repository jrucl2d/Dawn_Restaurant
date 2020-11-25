package com.dawn.service;

import com.dawn.dto.MenuDTO;
import com.dawn.model.Menu;

import java.util.List;

public interface MenuService {

    public Menu addMenus(MenuDTO.Create menuDTOs);
    public List<MenuDTO.Get> getAllMenusOfStore(int storeId);
    public List<MenuDTO.Get> getAllMenus();
    public MenuDTO.Get getMenuByMenuId(int menuId);
    public void removeMenus(List<MenuDTO.Remove> menuDTOs);
    public void removeMenu(int menuId);
}
