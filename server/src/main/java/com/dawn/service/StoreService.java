package com.dawn.service;

import com.dawn.dto.OrderDTO;
import com.dawn.dto.StoreDTO;
import com.dawn.exception.DawnException;
import com.dawn.model.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {

    public List<StoreDTO.GetStore> getAllStoreOfUserByUserId(int userId);
    public Store createStore(StoreDTO.CreateStore newStore);
    public OrderDTO.Get submitNewOrder(OrderDTO.Create newOrder) throws DawnException;
    public void removeAllOrderOfStore(int storeId);
    public void removeAllStore(List<StoreDTO.DeleteStore> targetStores);
    public void removeStoreByStoreId(int storeId);
    public void removeAllStoreOfUserByUserId(int userId);
}
