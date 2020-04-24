package com.meiyukai.controller.buyer_controller;

import com.meiyukai.converter.OrderForm2OrderDTOConverter;
import com.meiyukai.domain.*;
import com.meiyukai.dto.Item;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.dto.OrderStatusDetail;
import com.meiyukai.enums.ProductStatus;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.form.OrderForm;
import com.meiyukai.service.*;
import com.meiyukai.utils.JsonUtil;
import com.meiyukai.utils.ResultVOUtil;
import com.meiyukai.viewobject.ProductInfoVO;
import com.meiyukai.viewobject.ProductVO;
import com.meiyukai.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = "/buyer")
public class PagesController {

    @Resource(name = "productInfoService")
    private ProductInfoService productInfoService;

    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "buyerService")
    private BuyerService buyerService;

    @Resource(name = "expressService")
    private ExpressService expressService;

    @Resource(name = "sfExpressService")
    private SFExpressService sfExpressService;


    /**
     * 跳转购买页面
     * @return
     */
    @GetMapping(value = "/index")
    public ModelAndView toIndex(@RequestParam(value = "categoryType" , defaultValue = "1") Integer categoryType,
                                                            Map<String,Object> map){
        ModelAndView mav=  new ModelAndView();
        //查询特定类目下的商品
        List<ProductInfo> productInfoList = productInfoService.findByProductStatusAndAndCategoryType(ProductStatus.UP.getCode(), categoryType);
        //查询上架商品类目
        List<ProductInfo> upProductInfoList =  productInfoService.findUpAll();
        List<Integer> categoryTypeList = upProductInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList );

        map.put("productInfoList" , productInfoList);
        map.put("productCategoryList" , productCategoryList);
        map.put("currentCategoryType", categoryType);
        mav.addAllObjects(map);
        mav.setViewName("buyer/index");
        return mav;
    }


    @PostMapping(value = "/submit")
    public ModelAndView submit(Item item , Map<String ,Object> map  ){
//        log.info("【提交购物篮 和 openid】 item = {}"  , JsonUtil.toJson(item));
        ModelAndView mav =  new ModelAndView();
        map.put("items" , JsonUtil.toJsonCommon(item.getOrderDetailList()));
        map.put("openid" , item.getOpenid());
        mav.addAllObjects(map);
        mav.setViewName("buyer/receiving-adress");
        return mav;
    }



    @GetMapping(value = "/list")  // 相当于 ReqeustMapping  的get 请求
    public ResultVO list(){

        //1.查询所有的上架产品
        List<ProductInfo> productInfoList =  productInfoService.findUpAll();
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


    /**
     * 跳转"我的"界面
     * @return
     */
   @GetMapping(value = "/mine")
    public ModelAndView toMinePage(){
       ModelAndView mav = new ModelAndView();
       mav.setViewName("buyer/personal-center");
       return mav;
   }

    /**
     * 前端 ajax 加载数据 待收货 待发货 待付款等信息
     * @param openid
     * @return
     */
   @GetMapping(value = "/orderDetail")
    public String orderDetail( @RequestParam(value = "openid") String openid ){
//        log.info("openid = {}" , openid);
       List<OrderMaster> orderMasterList = orderService.findAll(openid);
       OrderStatusDetail orderStatusDetail = buyerService.find(orderMasterList);
       return JsonUtil.toJsonCommon(orderStatusDetail);
   }


    /**
     * 进入全部订单
     * @param map
     * @return
     */
   @GetMapping(value = "/allOrder")
    public ModelAndView allOrder(@RequestParam(value = "openid") String openid , Map<String, Object> map){
        ModelAndView mav = new ModelAndView();
       List<OrderDTO> orderDTOList = orderService.findAllByOpenid(openid);
       OrderStatusDetail orderStatusDetail = buyerService.find2(orderDTOList);
       map.put("orderDTOList" , orderDTOList);
       map.put("orderStatusDetail" , orderStatusDetail);
       mav.setViewName("buyer/my-indent-all");
        return mav;
   }

   @GetMapping(value = "/waitToPay")
   public ModelAndView waitToPay(@RequestParam(value = "openid") String openid , Map<String , Object >map){
       ModelAndView mav = new ModelAndView();
       List<OrderDTO> wait2PayList = buyerService.findAllWait2Pay(openid);

       List<OrderMaster> orderMasterList = orderService.findAll(openid);
       OrderStatusDetail orderStatusDetail = buyerService.find(orderMasterList);
       map.put("waitToPayList" , wait2PayList);
       map.put("orderStatusDetail" , orderStatusDetail);
       mav.addAllObjects(map);
       mav.setViewName("buyer/my-indent-dfk");
       return mav;
   }

   @GetMapping(value = "/waitToDeliver")
   public ModelAndView waitToDeliver(@RequestParam(value = "openid") String openid , Map<String, Object> map){
       ModelAndView mav = new ModelAndView();
       List<OrderDTO> wait2PayList = buyerService.findAllWait2Deliver(openid);

       List<OrderMaster> orderMasterList = orderService.findAll(openid);
       OrderStatusDetail orderStatusDetail = buyerService.find(orderMasterList);
       map.put("waitToDeliverList" , wait2PayList);
       map.put("orderStatusDetail" , orderStatusDetail);
       mav.addAllObjects(map);
       mav.setViewName("buyer/my-indent-dfh");
       return mav;

   }

   @GetMapping(value = "/waitToReceive")
   public ModelAndView waitToReceive(@RequestParam(value = "openid")String openid , Map<String ,Object> map){
       ModelAndView mav = new ModelAndView();
       List<OrderDTO> wait2ReceiveList = buyerService.findAllWait2Receive(openid);

       List<OrderMaster> orderMasterList = orderService.findAll(openid);
       OrderStatusDetail orderStatusDetail = buyerService.find(orderMasterList);
       map.put("wait2ReceiveList" , wait2ReceiveList);
       map.put("orderStatusDetail" , orderStatusDetail);
       mav.addAllObjects(map);
       mav.setViewName("buyer/my-indent-dsh");
       return mav;
   }


   @GetMapping(value = "/orderTrace")
   public ModelAndView orderTrace(@RequestParam(value = "orderId") String orderId , Map<String ,Object> map){
       log.info("orderId = {}" , orderId );
       ModelAndView mav = new ModelAndView();
       Express express = expressService.findByOrderId(orderId);
       OrderDTO orderDTO = orderService.findOne(orderId);
       SFExpress sfExpress = sfExpressService.getSFExpress(express.getExpressNumber(), orderDTO.getBuyerPhone());
       List<Info> traceData = sfExpress.getShowapi_res_body().getData(); //物流信息
       map.put("traceData" , traceData);
       map.put("expressNumber" , express.getExpressNumber());
       map.put("orderId" , orderDTO.getOrderId());
       map.put("traceSize" , traceData.size());
       mav.addAllObjects(map);
       mav.setViewName("buyer/order-tracking");
       return mav;
   }












}
