package com.dawn.repository.order;

import com.dawn.dto.OrderDTO;
import com.dawn.model.DawnOrder;

import java.util.List;

public interface CustomDawnOrderRepository {

    public void deleteAllOrderOfStore(int storeId);
    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order);
    public List<DawnOrder> findByStoreId(int storeId);

}
