package com.meiyukai.dao;

import com.meiyukai.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "productInfoRepository")
public interface ProductInfoRepository extends JpaRepository<ProductInfo  , String> , JpaSpecificationExecutor<ProductInfo> {


    List<ProductInfo> findByProductStatus(Integer productStatus);

}
