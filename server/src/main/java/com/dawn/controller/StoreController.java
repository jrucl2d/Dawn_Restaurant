package com.dawn.controller;

import com.dawn.common.DawnCodingError;
import com.dawn.common.DawnCodingResult;
import com.dawn.common.DawnErrorType;
import com.dawn.dto.MenuDTO;
import com.dawn.dto.MenuOrderDTO;
import com.dawn.dto.OrderDTO;
import com.dawn.dto.StoreDTO;
import com.dawn.exception.DawnException;
import com.dawn.model.Store;
import com.dawn.service.MenuService;
import com.dawn.service.StoreService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.dawn.common.DawnErrorType.INVALID_REQUEST_BODY;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final MenuService menuService;
    private final StoreService storeService;

    @Component
    public static class StringToUserConverter implements Converter<String, StoreDTO.CreateStore> {

        @Autowired
        private ObjectMapper objectMapper;

        @Override
        @SneakyThrows
        public StoreDTO.CreateStore convert(String source) {
            return objectMapper.readValue(source, StoreDTO.CreateStore.class);
        }

        @Override
        public JavaType getInputType(TypeFactory typeFactory) {
            return null;
        }

        @Override
        public JavaType getOutputType(TypeFactory typeFactory) {
            return null;
        }
    }

    @PostMapping(value = "/stores")
    //public ResponseEntity<DawnCodingResult> createNewStores(@RequestBody List<StoreDTO.CreateStore> newStores) {
    public ResponseEntity<DawnCodingResult> createNewStores(
            @RequestPart("store") String storeString,
            @RequestPart("profileImage") MultipartFile profileImage) throws IOException {
        storeString = new String(storeString.getBytes("8859_1"), StandardCharsets.UTF_8);
        List<Store> result = new ArrayList<>();
        StoreDTO.CreateStore newStores = new ObjectMapper().readValue(storeString, StoreDTO.CreateStore.class);
        Store newStore = storeService.createStore(newStores, profileImage);
        /*
        newStores.parallelStream()
                .forEach(x -> {
                    Store newStore = null;
                    try {
                        newStore = storeService.createStore(x);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    result.add(newStore);
                });
        */
        return new ResponseEntity<>(new DawnCodingResult<>(null, newStore.getStoreId()), HttpStatus.OK); // 여기 임시로 생성한 storeId 리턴하게 바꿔놨어~
    }

    @DeleteMapping("/stores")
    public ResponseEntity<DawnCodingResult> deleteAllStores(@RequestBody List<StoreDTO.DeleteStore> targetStores) {
        storeService.removeAllStore(targetStores);
        return new ResponseEntity<>(DawnCodingResult.OK(), HttpStatus.OK);
    }

    @GetMapping("/stores/users/{userId}")
    public ResponseEntity<DawnCodingResult> getAllStoresOfUser(@PathVariable("userId") int userId) {
        return new ResponseEntity<>(
                new DawnCodingResult<>(null, storeService.getAllStoreOfUserByUserId(userId)), HttpStatus.OK);
    }

    @DeleteMapping("/stores/store/{storeId}")
    public ResponseEntity<DawnCodingResult> deleteStoreByStoreId(@PathVariable("storeId") int storeId) {
        storeService.removeStoreByStoreId(storeId);
        return new ResponseEntity<>(DawnCodingResult.OK(), HttpStatus.OK);
    }

    @GetMapping("/stores/store/{storeId}/menus")
    public ResponseEntity<List<MenuDTO.GetMenu>> getMenusOfStore(@PathVariable("storeId") int storeId) {
        return new ResponseEntity<>(menuService.getAllMenusOfStore(storeId), HttpStatus.OK);
    }

    @PostMapping("/stores/orders")
    public ResponseEntity<DawnCodingResult> createOrdersOfStore(@RequestBody OrderDTO.Create newOrder) {
        if (newOrder.getMenusOrders() == null || newOrder.getMenusOrders().size() == 0) {
            return new ResponseEntity<>(
                    new DawnCodingResult<>(
                            new DawnCodingError(
                                    INVALID_REQUEST_BODY,
                                    "주문에 포함된 [메뉴,갯수] 쌍의 갯수는 0개 일 수 없습니다"), null),
                            HttpStatus.BAD_REQUEST);
        }
        for (MenuOrderDTO.Create menu : newOrder.getMenusOrders()) {
            if (menu.getQuantity() <= 0) {
                return new ResponseEntity<>(
                        new DawnCodingResult<>(
                                new DawnCodingError(
                                        INVALID_REQUEST_BODY,
                                        "[메뉴, 갯수] 쌍에서 갯수는 1보다 작을 수 없습니다. causation: [" + menu.getMenuId() + ","+ menu.getQuantity()+"]"), null),
                                        HttpStatus.BAD_REQUEST);
            }
        }

        try {
            OrderDTO.Get result = storeService.submitNewOrder(newOrder);
            return new ResponseEntity<>(
                    new DawnCodingResult<>(null, result), HttpStatus.OK);
        } catch (DawnException e) {
            return new ResponseEntity<>(
                    new DawnCodingResult<>(new DawnCodingError(e), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/stores/store/{storeId}/orders")
    public ResponseEntity<DawnCodingResult> deleteAllOrderOfStore(@PathVariable("storeId") int storeId) {
        if (storeId < 1) {
            return new ResponseEntity<>(new DawnCodingResult<>(
                    new DawnCodingError(
                            DawnErrorType.INVALID_PATH_PARAMETER,
                            "causation: storeId =[" + storeId + "]"),
                    HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        storeService.removeAllOrderOfStore(storeId);
        return new ResponseEntity<>(DawnCodingResult.OK(), HttpStatus.OK);
    }
}
