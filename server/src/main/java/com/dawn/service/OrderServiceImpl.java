package com.dawn.service;

import com.dawn.dto.OrderDTO;
import com.dawn.repository.order.DawnOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final DawnOrderRepository orderRepository;

    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order) {
        orderRepository.updateStateOfOrder(order);
    }

    public List<Object[]> getAllTotal(){
        List<Object[]> result = orderRepository.getAllTotal();
        return result;
    }

}
