package com.dawn.repository.storecategory;

import com.dawn.model.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Integer>, CustomStoreCategory {
}
