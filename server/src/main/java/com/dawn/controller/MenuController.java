package com.dawn.controller;

import com.dawn.dto.MenuDTO;
import com.dawn.model.Menu;
import com.dawn.service.MenuService;
import com.dawn.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final StoreService storeService;

    @GetMapping("/menus")
    public ResponseEntity<List<MenuDTO.Get>> menus() {
        return new ResponseEntity<>(menuService.getAllMenus(), HttpStatus.OK);
    }

    @PostMapping("/menus")
    public ResponseEntity<List<Menu>> addMenus(@RequestBody List<MenuDTO.Create> newMenus) {
        return new ResponseEntity<>(menuService.addMenus(newMenus), HttpStatus.OK);
    }

    @DeleteMapping("/menus")
    public ResponseEntity deleteMenus(@RequestBody List<MenuDTO.Remove> menus) {
        menuService.removeMenus(menus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/menus/menu/{menuId}")
    public ResponseEntity<MenuDTO.Get> getMenuByMenuId(@PathVariable("menuId") int menuId) {
        return new ResponseEntity<MenuDTO.Get>(menuService.getMenuByMenuId(menuId), HttpStatus.OK);
    }
}
