package com.dawn.service;

import com.dawn.dto.OrderDTO;

public interface OrderService {

    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order);

}
