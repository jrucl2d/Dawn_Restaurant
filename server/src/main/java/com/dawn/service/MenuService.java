package com.dawn.service;

import com.dawn.dto.MenuDTO;

import java.util.List;

public interface MenuService {

    public void addMenus(List<MenuDTO.Create> menuDTOs);
    public List<MenuDTO.Get> getAllMenusOfStore(int storeId);
    public List<MenuDTO.Get> getAllMenus();
    public void removeMenus(List<MenuDTO.Remove> menuDTOs);

}
