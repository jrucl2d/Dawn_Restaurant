package com.dawn.service;

import com.dawn.dto.MenuDTO;
import com.dawn.model.Menu;
import com.dawn.model.Store;
import com.dawn.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public void addMenus(List<MenuDTO.Create> menuDTOs) {
        for (MenuDTO.Create menuDTO : menuDTOs) {
            menuRepository.save(
                    new Menu(menuDTO.getMenuTitle(), menuDTO.getMenuDescription(),
                            menuDTO.getPrice(), "", new Store(menuDTO.getStoreId())));
        }
    }

    @Override
    public List<MenuDTO.Get> getAllMenusOfStore(int storeId) {
        List<Menu> menus = menuRepository.findByStoreId(storeId);
        List<MenuDTO.Get> result = new ArrayList<>();
        for (Menu menu : menus) {
            result.add(
                    new MenuDTO.Get(
                            menu.getMenuId(), menu.getStore().getStoreId(),
                            menu.getMenuTitle(), menu.getMenuDescription(),
                            menu.getPrice(), menu.getImageURL()));
        }
        return result;
    }

    @Override
    public List<MenuDTO.Get> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuDTO.Get> result = new ArrayList<>();
        for (Menu menu : menus) {
            result.add(
                    new MenuDTO.Get(
                            menu.getMenuId(), menu.getStore().getStoreId(),
                            menu.getMenuTitle(), menu.getMenuDescription(),
                            menu.getPrice(), menu.getImageURL()));
        }
        return result;
    }

    @Override
    public void removeMenus(List<MenuDTO.Remove> menuDTOs) {
        for (MenuDTO.Remove menuDTO : menuDTOs) {
            menuRepository.deleteById(menuDTO.getMenuId());
        }
    }

}
