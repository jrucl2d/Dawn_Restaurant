package com.dawn.repository.store;

import com.dawn.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>, CustomStoreRepository {
}
