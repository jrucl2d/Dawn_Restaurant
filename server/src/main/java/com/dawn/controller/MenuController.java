package com.dawn.controller;

import com.dawn.dto.MenuDTO;
import com.dawn.service.MenuService;
import com.dawn.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final StoreService storeService;

    @GetMapping("/menus")
    public ResponseEntity<List<MenuDTO.GetMenu>> menus() {
        return new ResponseEntity<>(menuService.getAllMenus(), HttpStatus.OK);
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuDTO.GetMenu> addMenu(@RequestPart("menu") String newMenuString,
                                                   @RequestPart("menuImage") MultipartFile menuImage) throws IOException {
        MenuDTO.CreateMenu newMenu = new ObjectMapper().readValue(newMenuString, MenuDTO.CreateMenu.class);
        return new ResponseEntity<>(menuService.addMenu(newMenu, menuImage), HttpStatus.OK);
    }

    @DeleteMapping("/menus")
    public ResponseEntity deleteMenus(@RequestBody List<MenuDTO.Remove> menus) {
        menuService.removeMenus(menus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/menus/menu/{menuId}")
    public ResponseEntity<MenuDTO.GetMenu> getMenuByMenuId(@PathVariable("menuId") int menuId) {
        return new ResponseEntity<MenuDTO.GetMenu>(menuService.getMenuByMenuId(menuId), HttpStatus.OK);
    }
}
