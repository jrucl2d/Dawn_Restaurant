package com.dawn.repository.order;

import com.dawn.dto.OrderDTO;
import com.dawn.model.DawnOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DawnOrderRepository extends JpaRepository<DawnOrder, Integer>, CustomDawnOrderRepository {

    @Query(value = "select store_title,  sum(total_price) from dawn_order, store where store.store_id=dawn_order.store_store_id and store.user_id=?1 and dawn_order.order_status=4 group by store_store_id", nativeQuery = true)
    List<Object[]> getAllTotal(int userId);

    @Modifying
    @Query(value="update dawn_order set order_status=?1 where dawn_order_id=?2", nativeQuery = true)
    void updateState(int orderStatusCode, int orderId);
}
