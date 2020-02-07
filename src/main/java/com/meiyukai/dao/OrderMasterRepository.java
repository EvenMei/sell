package com.meiyukai.dao;

import com.meiyukai.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository(value = "orderMasterRepository")
public interface OrderMasterRepository extends JpaRepository<OrderMaster , String> , JpaSpecificationExecutor<OrderMaster> {

    Page<OrderMaster>  findByBuyerOpenid(String buyerOpenid , Pageable pageable);

    /*OrderMaster findOne(String orderId);*/

}
