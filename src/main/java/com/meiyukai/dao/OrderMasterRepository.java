package com.meiyukai.dao;

import com.meiyukai.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "orderMasterRepository")
public interface OrderMasterRepository extends JpaRepository<OrderMaster , String> , JpaSpecificationExecutor<OrderMaster> {

    Page<OrderMaster>  findByBuyerOpenid(String buyerOpenid , Pageable pageable);

    List<OrderMaster> findAllByBuyerOpenid(String openid);

    List<OrderMaster> findAllByBuyerOpenidAndPayStatusAndOrderStatus(String openid , Integer payStatus  , Integer orderStatus);

    void deleteByOrderId(String orderId);

    /*OrderMaster findOne(String orderId);*/

}
