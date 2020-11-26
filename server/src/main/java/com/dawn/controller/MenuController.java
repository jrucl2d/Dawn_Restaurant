package com.dawn.controller;

import com.dawn.common.DawnCodingResult;
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
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final StoreService storeService;

    @GetMapping("/menus")
    public ResponseEntity<DawnCodingResult<List<MenuDTO.GetMenu>>> menus() {
        return new ResponseEntity<>(
                new DawnCodingResult<>(null, menuService.getAllMenus()), HttpStatus.OK);
    }

    @PostMapping("/menu")
    public ResponseEntity<DawnCodingResult<MenuDTO.GetMenu>> addMenu(@RequestPart("menu") String newMenuString,
                                                   @RequestPart("menuImage") MultipartFile menuImage) throws IOException {
        newMenuString = new String(newMenuString.getBytes("8859_1"), StandardCharsets.UTF_8);

        MenuDTO.CreateMenu newMenu = new ObjectMapper().readValue(newMenuString, MenuDTO.CreateMenu.class);
        return new ResponseEntity<>(
                new DawnCodingResult<>(null, menuService.addMenu(newMenu, menuImage)), HttpStatus.OK);
    }

    @PutMapping("/menu")
    public ResponseEntity<DawnCodingResult<MenuDTO.GetMenu>> updateMenu(@RequestBody MenuDTO.UpdateMenu menu) throws IOException {
        MenuDTO.GetMenu updatedMenu = menuService.updateMenu(menu);
        return new ResponseEntity<>(
                new DawnCodingResult<>(null, updatedMenu), HttpStatus.OK);
    }

    @DeleteMapping("/menus")
    public ResponseEntity deleteMenus(@RequestBody List<MenuDTO.Remove> menus) {
        menuService.removeMenus(menus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/menu/{menuId}")
    public ResponseEntity deleteMenus(@PathVariable int menuId) {
        menuService.removeMenu(menuId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/menus/menu/{menuId}")
    public ResponseEntity<DawnCodingResult<MenuDTO.GetMenu>> getMenuByMenuId(@PathVariable("menuId") int menuId) {
        return new ResponseEntity<>(
                new DawnCodingResult<>(null, menuService.getMenuByMenuId(menuId)), HttpStatus.OK);
    }
}
