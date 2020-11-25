package com.dawn.repository.order;

import com.dawn.dto.OrderDTO;
import com.dawn.model.Order;

import java.util.List;

public interface CustomOrderRepository {

    public void deleteAllOrderOfStore(int storeId);
    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order);
    public List<Order> findByStoreId(int storeId);

}
