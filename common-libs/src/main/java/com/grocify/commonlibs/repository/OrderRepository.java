package com.grocify.commonlibs.repository;

import com.grocify.commonlibs.entity.OrderEntity;
import com.grocify.commonlibs.enums.OrderStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

}
