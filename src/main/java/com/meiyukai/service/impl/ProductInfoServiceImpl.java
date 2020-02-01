package com.meiyukai.service.impl;

import com.meiyukai.dao.ProductInfoRepository;
import com.meiyukai.domain.ProductInfo;
import com.meiyukai.enums.ProductStatus;
import com.meiyukai.service.ProductInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {
    @Resource(name = "productInfoRepository")
    private ProductInfoRepository productInfoRepository;


    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAllByPage(Pageable pageable) {

        return productInfoRepository.findAll(pageable);
    }


    @Override
    public ProductInfo findProductInfoById(String  id) {
        return productInfoRepository.findById(id).get();
    }

    @Override
    public ProductInfo saveProductInfo(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }


}
