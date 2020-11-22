package com.dawn.service;

import com.dawn.dto.OrderDTO;
import com.dawn.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order) {
        orderRepository.updateStateOfOrder(order);
    }
}
