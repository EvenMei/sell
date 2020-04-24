package com.meiyukai.dao;

import com.meiyukai.domain.Express;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "expressRepository")
public interface ExpressRepository extends JpaRepository<Express, String> {

    /**
     * 根据orderId 查询订单
     * @param orderID
     * @return
     */
    Express findByOrderId(String orderID);

}
