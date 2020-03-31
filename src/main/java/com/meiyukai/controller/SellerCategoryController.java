package com.meiyukai.controller;


import com.meiyukai.domain.ProductCategory;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.service.ProductCategoryService;
import com.meiyukai.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping(value = "/seller/category")
@Slf4j
public class SellerCategoryController {

    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;


    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page ,
                                                    @RequestParam(value = "size" , defaultValue = "10") Integer size,
                                                    Map<String , Object> map){
        Page<ProductCategory> productCategoryPage = productCategoryService.findAll(PageRequest.of(page - 1, size));
        ModelAndView mav = new ModelAndView();

        map.put("productCategoryPage"  , productCategoryPage);
        map.put("currentPage" , page);
        map.put("size" ,size);

        mav.addAllObjects(map);
        mav.setViewName("category/list");
        return mav;
    }


    @GetMapping(value = "/update")
    public ModelAndView update(@RequestParam(value = "categoryId") Integer categoryId , Map<String , Object> map){
        ModelAndView mav = new ModelAndView();
        ProductCategory productCategory = productCategoryService.findProductCategoryById(categoryId);
        map.put("productCategory" ,productCategory);
        mav.setViewName("category/update");
        return mav;
    }

    @PostMapping(value = "/submit")
    public ModelAndView submit(@RequestParam(value = "categoryId") Integer categoryId ,
                                                          @RequestParam(value = "categoryType") Integer categoryType ,
                                                          @RequestParam(value = "categoryName") String categoryName,
                                                          Map<String , Object> map){
        ModelAndView mav =  new ModelAndView();
        ProductCategory productCategory =  new ProductCategory();
        try{
            productCategory.setCategoryName(categoryName);
            productCategory.setCategoryType(categoryType);
            productCategory.setCategoryId(categoryId);
            productCategoryService.save(productCategory);
        }catch(Exception e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/category/update?categoryId="+categoryId);
            mav.addAllObjects(map);
            mav.setViewName("common/error");
        }
        map.put("msg" , "修改成功！");
        map.put("url" , "/sell/seller/category/list");
        mav.addAllObjects(map);
        mav.setViewName("common/success");
        return mav;
    }

    /**
     * 删除类目
     * @return
     */
    @GetMapping(value = "/delete")
    public ModelAndView deleteCategory(@RequestParam(value = "categoryId") Integer categoryId , Map<String , Object> map){
        ModelAndView mav = new ModelAndView();
        try{
            productCategoryService.deleteProductCategoryById(categoryId);
        }catch(Exception e){
            map.put("msg" , e.getMessage()) ;
            map.put("url" , "/sell/seller/category/list");
            mav.addAllObjects(map);
            mav.setViewName("common/error");
        }
        map.put("msg" ,  "类目删除成功！");
        map.put("url" , "/sell/seller/category/list") ;
        mav.addAllObjects(map);
        mav.setViewName("common/success");
        return mav;
    }


    @GetMapping(value = "/toCreate")
    public ModelAndView toCreate(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("category/create");
        return mav;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@RequestParam(value = "categoryName") String categoryName  ,
                                                        @RequestParam(value = "categoryType") Integer categoryType,
                                                         Map<String, Object> map){

        ModelAndView mav = new ModelAndView();

        if(categoryType<=0){
            log.error("【创建类目】 categoryType = {}"  , categoryType);
            throw new SellException(ResultEnum.CATEGORY_TYPE_ERROR);
        }else{
            try{

                ProductCategory productCategory = new ProductCategory();
                productCategory.setCategoryId(null);
                productCategory.setCategoryType(categoryType);
                productCategory.setCategoryName(categoryName);
                productCategoryService.save(productCategory);
                map.put("msg" ,"类目创建成功！");
                map.put("url" , "/sell/seller/category/list");
                mav.addAllObjects(map);
                mav.setViewName("common/success");
            }catch(Exception e){
                map.put("msg" , "类目创建失败！  "+e.getMessage());
                map.put("url" , "/sell/seller/category/toCreate");
                mav.addAllObjects(map);
                mav.setViewName("common/error");
            }

        }



        return mav;

    }







}
