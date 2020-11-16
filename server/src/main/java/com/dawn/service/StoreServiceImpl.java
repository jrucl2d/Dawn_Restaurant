package com.dawn.service;

import com.dawn.dto.StoreDTO;
import com.dawn.model.Store;
import com.dawn.model.User;
import com.dawn.repository.store.StoreRepository;
import com.dawn.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public Store createStore(StoreDTO.Create newStore) {
        User user = userRepository.findUserByUserId(newStore.getOwnerUserId());
        Store store =
                new Store(newStore.getStoreTitle(), newStore.getLocation(),
                          newStore.getBusinessHour(), newStore.getDescription(), user);
        return storeRepository.save(store);
    }

}

