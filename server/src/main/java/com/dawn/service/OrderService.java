package com.dawn.service;

import com.dawn.dto.OrderDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderService {

    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order);

    public List<Object[]> getAllTotal();

}
