package com.dawn.service;

import com.dawn.dto.OrderDTO;
import com.dawn.repository.order.DawnOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final DawnOrderRepository orderRepository;

    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order) {
        orderRepository.updateStateOfOrder(order);
    }

    @Transactional
    public void updateState(OrderDTO.OrderStateUpdate order){
        orderRepository.updateState(order.getOrderStatus().getStatusCode(), order.getOrderId());
    }

    public List<Object[]> getAllTotal(int userId){
        List<Object[]> result = orderRepository.getAllTotal(userId);
        return result;
    }

}
