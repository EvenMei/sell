package com.meiyukai.controller;

import com.meiyukai.domain.ProductInfo;
import com.meiyukai.service.ProductInfoService;
import com.meiyukai.utils.UploadFileUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;

@RestController
@RequestMapping(value = "/seller/product")
@Slf4j
public class SellerProductController {

    @Resource(name = "productInfoService")
    private ProductInfoService productInfoService;



    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value ="page"  , defaultValue = "1") Integer page ,
                                                    @RequestParam(value = "size" , defaultValue = "10") Integer size ,
                                                    Map<String , Object> map ){
        ModelAndView mav = new ModelAndView();
        Page<ProductInfo> productInfoPage = productInfoService.findAll(PageRequest.of(page-1, size));


        map.put("productInfoPage", productInfoPage);
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


    @GetMapping(value = "/update")
    public ModelAndView update(@RequestParam(value = "productId") String productId ,
                                                          Map<String , Object> map){
        ModelAndView mav = new ModelAndView();
        ProductInfo productInfo = productInfoService.findProductInfoById(productId);
        map.put("productInfo"  , productInfo);
        mav.addAllObjects(map);
        mav.setViewName("product/update");
        return mav;
    }



    @PostMapping(value = "/upload")
    public ModelAndView  upload(@RequestParam(value = "uploadFile") MultipartFile uploadFile ,
                                                           @RequestParam(value = "iconUrl") String iconUrl,
                                                           @RequestParam(value = "productId") String productId,
                                                            @RequestParam(value = "productName") String productName ,
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
            Client client = Client.create();
            WebResource webResource = client.resource(UploadFileUtils.IMG_SERVER_PATH + fileName);
            webResource.put(uploadFile.getBytes());

            ProductInfo productInfo = productInfoService.findProductInfoById(productId);
            productInfo.setProductIcon(UploadFileUtils.IMG_SERVER_PATH+fileName);
            productInfoService.saveProductInfo(productInfo);

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



}
