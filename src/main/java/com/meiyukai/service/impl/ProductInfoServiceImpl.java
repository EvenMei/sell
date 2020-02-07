package com.meiyukai.service.impl;

import com.meiyukai.dao.ProductInfoRepository;
import com.meiyukai.domain.ProductInfo;
import com.meiyukai.dto.CartDTO;
import com.meiyukai.enums.ProductStatus;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.service.ProductInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            if (productInfo==null){
                throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
            }
            Integer result = cartDTO.getProductQuantity()+productInfo.getProductStock();
            if (result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }


    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO  :  cartDTOList){
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result =  productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0 ){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

        }
    }


}
