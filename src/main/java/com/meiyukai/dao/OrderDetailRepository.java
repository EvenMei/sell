package com.meiyukai.dao;

import com.meiyukai.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "orderDetailRepository")
public interface OrderDetailRepository extends JpaRepository<OrderDetail , String> , JpaSpecificationExecutor<OrderDetail> {


    List<OrderDetail> findByOrderId(String orderId);
}
