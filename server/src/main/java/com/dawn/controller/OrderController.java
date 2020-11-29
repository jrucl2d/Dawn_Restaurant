package com.dawn.controller;

import com.dawn.common.DawnCodingResult;
import com.dawn.dto.OrderDTO;
import com.dawn.service.OrderService;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PutMapping("/order")
    @JsonCreator
    public ResponseEntity<DawnCodingResult> changeStateOfOrder(@RequestBody OrderDTO.OrderStateUpdate order) {
//        orderService.updateStateOfOrder(order);
            orderService.updateState(order);
        return new ResponseEntity<>(DawnCodingResult.OK(), HttpStatus.OK);
    }

    @GetMapping("/orderTotal/{userId}")
    public List<Object[]> getAllOrderTotal(@PathVariable int userId){
        List<Object[]> result = orderService.getAllTotal(userId);
        result.forEach(v ->{
            System.out.println(Arrays.toString(v));
        });
        return result;
    }
}
