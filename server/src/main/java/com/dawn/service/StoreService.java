package com.dawn.service;

import com.dawn.dto.OrderDTO;
import com.dawn.dto.SalesDTO;
import com.dawn.dto.StoreDTO;
import com.dawn.exception.DawnException;
import com.dawn.model.Store;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface StoreService {

    public List<StoreDTO.GetStore> getAllStoreOfUserByUserId(int userId);
    public Store createStore(StoreDTO.CreateStore newStore, MultipartFile profileImage) throws IOException;
    public OrderDTO.GetOrder submitNewOrder(OrderDTO.CreateOrder newOrder) throws DawnException;
    public void removeAllOrderOfStore(int storeId);
    public void removeAllStore(List<StoreDTO.DeleteStore> targetStores);
    public void removeStoreByStoreId(int storeId);
    public void removeAllStoreOfUserByUserId(int userId);
    public SalesDTO.GetSales getSalesOfStore(int storeId);
}
