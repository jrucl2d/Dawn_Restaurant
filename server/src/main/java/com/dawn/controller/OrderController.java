package com.dawn.controller;

import com.dawn.common.DawnCodingResult;
import com.dawn.dto.OrderDTO;
import com.dawn.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    public ResponseEntity<DawnCodingResult> changeStateOfOrder(@RequestBody List<OrderDTO.OrderStateUpdate> orders) {
        for (OrderDTO.OrderStateUpdate order : orders) {
            orderService.updateStateOfOrder(order);
        }
        return new ResponseEntity<>(DawnCodingResult.OK(), HttpStatus.OK);
    }
}
