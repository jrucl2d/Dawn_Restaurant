package com.dawn.service;

import com.dawn.common.CloudConstants;
import com.dawn.dto.MenuDTO;
import com.dawn.model.Menu;
import com.dawn.model.Store;
import com.dawn.repository.menu.MenuRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public MenuDTO.GetMenu addMenu(MenuDTO.CreateMenu menuDTO, MultipartFile menuImageFile) throws IOException {
        Menu newMenu = menuRepository.save(new Menu(menuDTO.getMenuTitle(), menuDTO.getMenuDescription(),
                menuDTO.getPrice(), "", new Store(menuDTO.getStoreId())));
        if (menuImageFile != null) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CloudConstants.KEYFILE_PATH))
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

            String menuImageName = "menu/" + newMenu.getMenuId() + "-image.jpg";
            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder("sogong", menuImageName).build(), menuImageFile.getBytes());
            System.out.println("generated blob = " + blobInfo.getName());
            newMenu.setImageFileName(menuImageName);
        }
        menuRepository.save(newMenu);
        return Menu.toMenuDTOGetMenu(newMenu);
    }

    @Override
    public List<MenuDTO.GetMenu> getAllMenusOfStore(int storeId) {
        List<Menu> menus = menuRepository.findByStoreId(storeId);
        List<MenuDTO.GetMenu> result = new ArrayList<>();
        for (Menu menu : menus) {
            result.add(
                    new MenuDTO.GetMenu(
                            menu.getMenuId(), menu.getStore().getStoreId(),
                            menu.getMenuTitle(), menu.getMenuDescription(),
                            menu.getPrice(), menu.getImageFileName()));
        }
        return result;
    }

    @Override
    public MenuDTO.GetMenu getMenuByMenuId(int menuId) {
        return Menu.toMenuDTOGetMenu(menuRepository.findByMenuId(menuId));
    }

    @Override
    public List<MenuDTO.GetMenu> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuDTO.GetMenu> result = new ArrayList<>();
        for (Menu menu : menus) {
            result.add(
                    new MenuDTO.GetMenu(
                            menu.getMenuId(), menu.getStore().getStoreId(),
                            menu.getMenuTitle(), menu.getMenuDescription(),
                            menu.getPrice(), menu.getImageFileName()));
        }
        return result;
    }

    @Override
    public MenuDTO.GetMenu updateMenu(MenuDTO.UpdateMenu menu) {
        Menu targetMenu = menuRepository.findByMenuId(menu.getMenuId());
        targetMenu.setMenuTitle(menu.getMenuTitle());
        targetMenu.setMenuDescription(menu.getMenuDescription());
        targetMenu.setPrice(menu.getPrice());
        return Menu.toMenuDTOGetMenu(menuRepository.save(targetMenu));
    }

    @Override
    public void removeMenus(List<MenuDTO.Remove> menuDTOs) {
        for (MenuDTO.Remove menuDTO : menuDTOs) {
            menuRepository.deleteById(menuDTO.getMenuId());
        }
    }

    @Override
    public void removeMenu(int menuId){
        menuRepository.deleteById(menuId);
    }


}
