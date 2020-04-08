package com.meiyukai.dao;

import com.meiyukai.domain.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository(value = "sellerInfoRepository")
public interface SellerInfoRepository extends JpaRepository<SellerInfo  , String> , JpaSpecificationExecutor<SellerInfo> {


    SellerInfo findByOpenid(String openid);

    SellerInfo findByUsername(String username);

}
