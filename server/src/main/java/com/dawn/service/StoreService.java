package com.dawn.service;

import com.dawn.dto.OrderDTO;
import com.dawn.dto.StoreDTO;
import com.dawn.exception.DawnException;
import com.dawn.model.Store;
import org.springframework.stereotype.Service;

@Service
public interface StoreService {

    public Store createStore(StoreDTO.Create newStore);
    public OrderDTO.Get submitNewOrder(OrderDTO.Create newOrder) throws DawnException;
    public void removeAllOrderOfStore(int storeId);
}
