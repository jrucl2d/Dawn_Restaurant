package com.dawn.service;

import com.dawn.dto.StoreDTO;
import com.dawn.model.Store;
import org.springframework.stereotype.Service;

@Service
public interface StoreService {

    public Store createStore(StoreDTO.Create newStore);
}
