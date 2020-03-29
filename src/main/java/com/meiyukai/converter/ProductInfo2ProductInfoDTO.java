package com.meiyukai.converter;


import com.meiyukai.domain.ProductCategory;
import com.meiyukai.domain.ProductInfo;
import com.meiyukai.dto.ProductInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class ProductInfo2ProductInfoDTO {

    public static  PageImpl<ProductInfoDTO> getProductInfoDTOPage(List<ProductInfo> productInfoList , List<ProductCategory> productCategoryList , Pageable pageable){
        List<ProductInfoDTO> productInfoDTOList = productInfoList.stream().map(e -> productInfo2ProductInfoDTO(e, productCategoryList)).collect(Collectors.toList());

       PageImpl<ProductInfoDTO> productInfoDTOPage = new PageImpl<ProductInfoDTO>(productInfoDTOList , pageable , productInfoDTOList.size());
        return productInfoDTOPage;
    }

    public static ProductInfoDTO productInfo2ProductInfoDTO(ProductInfo productInfo , List<ProductCategory> productCategoryList){
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        String categoryName = getCategoryNameINProductCategory(productCategoryList , productInfo.getCategoryType());
        BeanUtils.copyProperties(productInfo , productInfoDTO);
        productInfoDTO.setCategoryName(categoryName);
        return productInfoDTO;

    }

    /**
     * 从productCategoryList 中 获取 categoryName
     * @param productCategoryList 带查询的类型集合
     * @param categoryType 目标类型
     * @return
     */
    public static String  getCategoryNameINProductCategory(List<ProductCategory> productCategoryList , Integer categoryType){
        String categoryName = "";
        for (ProductCategory productCategory : productCategoryList){
            if(categoryType.equals(productCategory.getCategoryType())){
                categoryName = productCategory.getCategoryName();
            }
        }
        return categoryName;
    }







}
