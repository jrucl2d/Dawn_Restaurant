package com.dawn.controller;

import com.dawn.dto.MenuDTO;
import com.dawn.service.MenuService;
import com.dawn.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final StoreService storeService;

    @GetMapping("/menus")
    private ResponseEntity<List<MenuDTO.Get>> menus() {
        return new ResponseEntity<>(menuService.getAllMenus(), HttpStatus.OK);
    }
}
