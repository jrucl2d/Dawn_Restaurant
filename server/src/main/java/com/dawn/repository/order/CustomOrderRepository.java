package com.dawn.repository.order;

import com.dawn.dto.OrderDTO;

public interface CustomOrderRepository {

    public void deleteAllOrderOfStore(int storeId);
    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order);
}
