package com.meiyukai.service;

import com.meiyukai.domain.ProductInfo;
import com.meiyukai.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    /**
     *查询在架商品
     */

    public List<ProductInfo> findUpAll();


    /**
     * 查询所有的产品
     */
    public List<ProductInfo> findAll();



    /**
     * 分页显示
     */
    public Page<ProductInfo> findAll(Pageable pageable);



    /**
     * 根据ID查询商品
     */

    public ProductInfo findProductInfoById(String id);

    /**
     * 保存 productInfo 的方法
     * @param productInfo
     * @return
     */
    public ProductInfo saveProductInfo(ProductInfo productInfo);


    /**
     * 加库存
     */

    public void increaseStock(List<CartDTO> cartDTOList);




    /**
     * 减库存
     */

    public void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 根据 productId 删除商品信息
     */

    public void deleteProductById(String productId);


    /**
     * 根据ProductStatus 和 CategoryType 查询商品
     */
    List<ProductInfo>  findByProductStatusAndAndCategoryType(Integer productStatus , Integer categoryType);









}
