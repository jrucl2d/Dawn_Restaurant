package com.dawn.controller;

import com.dawn.common.DawnCodingResult;
import com.dawn.dto.MenuDTO;
import com.dawn.dto.OrderDTO;
import com.dawn.service.MenuService;
import com.dawn.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final MenuService menuService;
    private final StoreService storeService;

    @GetMapping("/stores/store/{storeId}/menus")
    public ResponseEntity<List<MenuDTO.Get>> getMenusOfStore(@PathVariable("storeId") int storeId) {
        return new ResponseEntity<>(menuService.getAllMenusOfStore(storeId), HttpStatus.OK);
    }

    @PostMapping("/stores/orders")
    public ResponseEntity<DawnCodingResult> createOrdersOfStore(@RequestBody OrderDTO.Create newOrder) {
        return new ResponseEntity<DawnCodingResult>(
                new DawnCodingResult<OrderDTO.Get>(null, storeService.submitNewOrder(newOrder)), HttpStatus.OK);
    }
}
