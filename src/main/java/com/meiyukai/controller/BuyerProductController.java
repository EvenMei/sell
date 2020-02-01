package com.meiyukai.controller;

import com.meiyukai.domain.ProductCategory;
import com.meiyukai.domain.ProductInfo;
import com.meiyukai.enums.ProductStatus;
import com.meiyukai.service.ProductCategoryService;
import com.meiyukai.service.ProductInfoService;
import com.meiyukai.utils.ResultVOUtil;
import com.meiyukai.viewobject.ProductInfoVO;
import com.meiyukai.viewobject.ProductVO;
import com.meiyukai.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Resource(name = "productInfoService")
    private ProductInfoService productInfoService;

    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;


    @GetMapping(value = "/list")  // 相当于 ReqeustMapping  的get 请求
    public ResultVO  list(){

//        ResultVO resultVO =  new ResultVO();




        //1.查询所有的上架产品
        List<ProductInfo> productInfoList =  productInfoService.findUpAll();

        //2. 查询类目(一次性)
        /*List<Integer> categoryTypeList = new ArrayList<>();
        for(ProductInfo productInfo : productInfoList){
            categoryTypeList.add(productInfo.getCategory_type());
        }*/


        /**
         * java 8 lambda 表达式
         */

        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList );


        //3.数据拼装
        List<ProductVO> productVOList  =  new ArrayList<>();

        for(ProductCategory category :  productCategoryList){
            ProductVO productVO =  new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            //判断 上架产品的categoryType 是否与category的相等
            for(ProductInfo productInfo : productInfoList){
                if(category.getCategoryType() == productInfo.getCategoryType() ){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo , productInfoVO);  // 拷贝属性值
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);

        }


        return ResultVOUtil.success(productVOList);

    }







}
