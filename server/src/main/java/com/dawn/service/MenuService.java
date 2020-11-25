package com.dawn.service;

import com.dawn.dto.MenuDTO;
import com.dawn.model.Menu;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MenuService {

    public MenuDTO.GetMenu addMenu(MenuDTO.CreateMenu menuDTO, MultipartFile menuImageFile) throws IOException;
    public List<MenuDTO.GetMenu> getAllMenusOfStore(int storeId);
    public List<MenuDTO.GetMenu> getAllMenus();
    public MenuDTO.GetMenu getMenuByMenuId(int menuId);
    public void removeMenus(List<MenuDTO.Remove> menuDTOs);
    public void removeMenu(int menuId);
}
