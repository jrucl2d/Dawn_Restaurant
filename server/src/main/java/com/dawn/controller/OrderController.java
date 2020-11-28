package com.dawn.controller;

import com.dawn.common.DawnCodingResult;
import com.dawn.dto.OrderDTO;
import com.dawn.service.OrderService;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PutMapping("/order")
    @JsonCreator
    public ResponseEntity<DawnCodingResult> changeStateOfOrder(@RequestBody OrderDTO.OrderStateUpdate order) {
        orderService.updateStateOfOrder(order);

        return new ResponseEntity<>(DawnCodingResult.OK(), HttpStatus.OK);
    }


}
