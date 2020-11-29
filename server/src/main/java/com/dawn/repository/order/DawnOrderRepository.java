package com.dawn.repository.order;

import com.dawn.model.DawnOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DawnOrderRepository extends JpaRepository<DawnOrder, Integer>, CustomDawnOrderRepository {

    @Query(value = "select store_title,  sum(total_price) from dawn_order, store where store.store_id=dawn_order.store_store_id group by store_store_id", nativeQuery = true)
    List<Object[]> getAllTotal();
}
