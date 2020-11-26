package com.dawn.repository.order;

import com.dawn.model.DawnOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DawnOrderRepository extends JpaRepository<DawnOrder, Integer>, CustomDawnOrderRepository {

}
