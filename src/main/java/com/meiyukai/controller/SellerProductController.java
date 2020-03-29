package com.meiyukai.controller;

import com.meiyukai.converter.ProductInfo2ProductInfoDTO;
import com.meiyukai.domain.ProductCategory;
import com.meiyukai.domain.ProductInfo;
import com.meiyukai.dto.ProductInfoDTO;
import com.meiyukai.enums.ProductStatus;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.service.ProductCategoryService;
import com.meiyukai.service.ProductInfoService;
import com.meiyukai.utils.UploadFileUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/seller/product")
@Slf4j
public class SellerProductController {

    @Resource(name = "productInfoService")
    private ProductInfoService productInfoService;

    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;



    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value ="page"  , defaultValue = "1") Integer page ,
                                                    @RequestParam(value = "size" , defaultValue = "10") Integer size ,
                                                    Map<String , Object> map ){
        ModelAndView mav = new ModelAndView();
        //查询所有的productInfo
        List<ProductInfo> productInfoList = productInfoService.findAll();

//        Page<ProductInfo> productInfoPage = productInfoService.findAll(PageRequest.of(page-1, size));

        //查询所有的productCategory
        List<ProductCategory> productCategoryList = productCategoryService.findAll();

        PageImpl<ProductInfoDTO> productInfoDTOPage = ProductInfo2ProductInfoDTO.getProductInfoDTOPage(productInfoList , productCategoryList , PageRequest.of(page-1 , size));

        map.put("productInfoDTOPage", productInfoDTOPage);
        map.put("currentPage"  , page);
        map.put("size" , size);
        mav.addAllObjects(map);
        mav.setViewName("product/list");

        return mav;
    }


    /**
     * 上下架状态的修改
     * @param productId
     * @param productStatus
     * @param map
     * @return
     */
    @GetMapping(value ="/modify")
    public ModelAndView modify(  @RequestParam(value = "productId") String productId  ,
                                                             @RequestParam(value = "productStatus") Integer productStatus ,
                                                             Map<String , Object> map){
        ModelAndView mav = new ModelAndView();
        ProductInfo productInfo = productInfoService.findProductInfoById(productId);
        productInfo.setProductStatus(Math.abs(productStatus-1));
        productInfoService.saveProductInfo(productInfo);

        map.put("msg" , "修改成功！");
        map.put("url",   "/sell/seller/product/list");
        mav.setViewName("common/success");
        return mav;
    }


    /**
     * 跳转更新界面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping(value = "/update")
    public ModelAndView update(@RequestParam(value = "productId") String productId ,
                                                          Map<String , Object> map){
        ModelAndView mav = new ModelAndView();
        ProductInfo productInfo = productInfoService.findProductInfoById(productId);
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productInfo"  , productInfo);
        map.put("productCategoryList" , productCategoryList);
        mav.addAllObjects(map);
        mav.setViewName("product/update");
        return mav;
    }


    /***
     *  提交更新商品信息
     * @param productId 商品的主键
     * @param productName 商品的名字
     * @param productPrice 商品的价格
     * @param productStock 商品的库存
     * @param productCategory 商品的种类
     * @param productDescription 对于商品的描述
     * @param map
     * @return
     */
    @PostMapping(value = "/submit")
    public ModelAndView submitMessages(@RequestParam(value = "productId")String productId,
                                                         @RequestParam(value = "productName") String productName,
                                                         @RequestParam(value ="productPrice" ) String productPrice,
                                                         @RequestParam(value = "productStock") Integer productStock,
                                                         @RequestParam(value = "productCategory")Integer productCategory,
                                                         @RequestParam(value = "productDescription") String productDescription,
                                                         Map<String , Object> map){
        ModelAndView mav  =  new ModelAndView();


        ProductInfo productInfo = productInfoService.findProductInfoById(productId);

        productInfo.setProductName(productName);
        productInfo.setProductPrice(new BigDecimal(productPrice));
        productInfo.setProductStock(productStock);
        productInfo.setCategoryType(productCategory);
        productInfo.setProductDescription(productDescription);
        try{
            productInfoService.saveProductInfo(productInfo);

        }catch (Exception e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/product/update?productId="+productId);
            mav.addAllObjects(map);
            mav.setViewName("common/error");
        }
        map.put("msg", "修改成功！");
        map.put("url" , "/sell/seller/product/update?productId="+productId);
        mav.addAllObjects(map);
        mav.setViewName("common/success");
        return mav;

    }


    /**
     * 商品信息的 图片上传
     * @param uploadFile 上传的文件
     * @param productId  产品的ID
     * @param map
     * @return
     */
    @PostMapping(value = "/upload")
    public ModelAndView  upload(@RequestParam(value = "uploadFile") MultipartFile uploadFile ,
                                                           @RequestParam(value = "productId") String productId,
                                                            @RequestParam(value = "iconPath") String iconPath,
                                                            Map<String,Object> map){
        ModelAndView mav = new ModelAndView();


        String msg = "上传成功！";

        if(uploadFile.isEmpty()){
            msg = "上传文件为空！请重新上传！";
            throw new RuntimeException("上传文件为空，请重新上传！");
        }

        String originalName = uploadFile.getOriginalFilename();
        log.info("【文件上传】文件名字为：" +originalName);

        //待上传的文件名
        String fileName = UploadFileUtils.getFileName(originalName);
        log.info("【fileName】 fileName  = {}"  , fileName);

        try{
            //先删除远程数据库中的照片
            if(UploadFileUtils.deleteFileByFileName(iconPath).equals("success")){
                //文件上传到远程服务器
                String productIcon = UploadFileUtils.uploadFileToServer(fileName, uploadFile);

                ProductInfo productInfo = productInfoService.findProductInfoById(productId);
                productInfo.setProductIcon(productIcon);
                productInfoService.saveProductInfo(productInfo);
            }else{
                throw new SellException(ResultEnum.DELETE_ICON_ERROR);
            }


        }catch (Exception e){
            log.error( "【文件上传】 文件上传失败 ：error =  {}" , e.getMessage());
            msg= e.getMessage();
            map.put("msg" , msg);
            map.put("url" , "/sell/seller/product/update?productId="+productId);
            mav.addAllObjects(map);
             mav.setViewName("common/error");
            return mav;
        }


        map.put("msg" , "文件正在上传，请稍等 ... ... ");
        map.put("url" , "/sell/seller/product/update?productId="+productId);
        mav.addAllObjects(map);
        mav.setViewName("common/success");
        return mav;
    }


    /**
     * 带着类目信息跳转到create.ftl
     * @param map
     * @return
     */

    @GetMapping(value = "/toCreate")
    public ModelAndView create(Map<String , Object> map){
        ModelAndView mav = new ModelAndView();
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList" , productCategoryList);
        mav.addAllObjects(map);
        mav.setViewName("product/create");
        return mav;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@RequestParam(value = "uploadFile") MultipartFile uploadFile ,
                       @RequestParam(value = "productName") String productName ,
                       @RequestParam(value = "productPrice")String productPrice,
                       @RequestParam(value = "productStock")Integer productStock,
                       @RequestParam(value="productDescription")String productDescription,
                       @RequestParam(value = "productCategory") Integer productCategory,
                       Map<String , Object> map){
        String productId = UUID.randomUUID().toString().replace("-","").substring(0,5);
        log.info("【productId】 product id ={} " , productId);
        ModelAndView mav = new ModelAndView();

        ProductInfo productInfo = new ProductInfo();
        try{
            //先构件商品信息
            productInfo.setProductId(productId);
            productInfo.setProductName(productName);
            productInfo.setProductPrice(new BigDecimal(productPrice));
            productInfo.setProductStock(productStock);
            productInfo.setProductDescription(productDescription);
            productInfo.setCategoryType(productCategory);
            productInfo.setProductStatus(ProductStatus.UP.getCode());
            //文件上传
            if (!uploadFile.isEmpty()){
                String originalName = uploadFile.getOriginalFilename();
                String finalName = UploadFileUtils.getFileName(originalName);
                String productIcon = UploadFileUtils.uploadFileToServer(finalName, uploadFile);
                productInfo.setProductIcon(productIcon);

            }
            //保存到数据库
            productInfoService.saveProductInfo(productInfo);
            //跳转商品列表

        }catch(Exception e){
            log.error("【创建商品】商品信息创建失败 e={}" , e.getMessage());
            map.put("msg" , e.getMessage());
            map.put("url","/sell/seller/product/toCreate");
            mav.addAllObjects(map);
            mav.setViewName("common/error");
            return mav;
        }


        map.put("msg" , "商品创建成功！");
        map.put("url" , "/sell/seller/product/list");
        mav.addAllObjects(map);
        mav.setViewName("common/success");
        return mav;

    }

    /**
     * 删除商品
     * @param productId 待删除的商品的ID
     * @param map
     * @return
     */
    @GetMapping(value = "/deleteProduct")
    public ModelAndView deleteProduct(@RequestParam(value = "productId") String productId ,
                                      @RequestParam(value = "iconPath") String iconPath,
                                      Map<String, Object> map){
        ModelAndView mav = new ModelAndView();
        log.info("【】productId = {}"  , productId);
        try{

            /*String result = UploadFileUtils.deleteFileByFileName("aaaa.png");
            log.info("【图片删除】 result = {}" , result);*/

            //先删除 远程服务器中的图片，在删除数据库中的信息
            if(UploadFileUtils.deleteFileByFileName(iconPath).equals("success")){
                productInfoService.deleteProductById(productId);
            }else{
                throw new SellException(ResultEnum.DELETE_ICON_ERROR);
            }


        }catch(Exception e){
            log.error("【商品删除】 商品删除失败！e= {}", e.getMessage());
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/product/list");
            mav.addAllObjects(map);
            mav.setViewName("common/error");
            return mav;
        }
        map.put("msg" , "商品删除成功！");
        map.put("url" , "/sell/seller/product/list");
        mav.addAllObjects(map);
        mav.setViewName("common/success");
      return mav;
    }




}
