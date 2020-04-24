package com.meiyukai.controller;
import com.meiyukai.config.ProjectUrl;
import com.meiyukai.converter.OrderForm2OrderDTOConverter;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.form.OrderForm;
import com.meiyukai.service.BuyerService;
import com.meiyukai.service.OrderService;
import com.meiyukai.service.impl.OrderServiceImpl;
import com.meiyukai.utils.ResultVOUtil;
import com.meiyukai.viewobject.ProductVO;
import com.meiyukai.viewobject.ResultVO;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "buyerService")
    private BuyerService buyerService;

    @Autowired
    private ProjectUrl projectUrl;



    //创建订单  (新UI中已经启用)
    /*@PostMapping(value = "/create")
    public ResultVO<Map<String , String>> createOrder(@Valid OrderForm orderForm , BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数错误  orderForm = {}" , orderForm );
            throw new SellException(ResultEnum.PARAM_ERROR.getCode() , bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空 错误 orderDTO  = {} " , orderDTO);
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createdOrderDTO = orderService.createOrder(orderDTO);
        Map<String  ,  String > map = new HashMap<>();
        map.put("orderId" , createdOrderDTO.getOrderId());
        return ResultVOUtil.success(map);
    }
*/

    /**
     * 根据提交的信息 创建订单
     * @param orderForm  提交的表单信息 ，收货信息
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping(value = "/createOrder")
    public ModelAndView create(@Valid OrderForm orderForm , BindingResult bindingResult , Map<String, Object> map){
        ModelAndView mav = new ModelAndView();
//        log.info("orderForm = {}" , orderForm);
        if (bindingResult.hasErrors()){
            log.error("【创建订单】表单有错误   errorinfo = {}", bindingResult.getFieldError().getDefaultMessage()  );
            throw new SellException(ResultEnum.PARAM_ERROR.getCode() , bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空 错误 orderDTO  = {} " , orderDTO);
            throw new SellException(ResultEnum.CART_EMPTY);
        }

//        log.info("orderDTO = {}"  , orderDTO);
        OrderDTO createOrderDTO = orderService.createOrder(orderDTO);
        //支付后跳转"我的"页面
        mav.setViewName("redirect:/pay/create?orderId="+orderDTO.getOrderId()+"&returnUrl="+projectUrl.getSell()+"buyer/mine");
        return mav;

    }



    // 订单列表

    @RequestMapping(value = "/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam(value = "openid") String buyerOpenid ,
                                                                           @RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                                                           @RequestParam(value = "size" , defaultValue = "10") Integer size){


        if (StringUtils.isEmpty(buyerOpenid)){
            log.error("【订单列表】微信号 不能为空  buyerOpenid={} "  , buyerOpenid);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageable = PageRequest.of(page , size);

        Page<OrderDTO> orderListPage = orderService.findOrderList(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList  =  orderListPage.getContent();


        return ResultVOUtil.success(orderDTOList);
    }



    //  订单详情
    @GetMapping(value = "/detail")
    public ResultVO<OrderDTO> detail(@RequestParam(value = "openid") String openid  , @RequestParam(value = "orderId") String orderId){

        OrderDTO orderDTO = buyerService.findOrderOne(openid , orderId);

        return ResultVOUtil.success(orderDTO);

    }



    //取消订单
    @GetMapping(value = "/cancel")
    public ModelAndView cancel(@RequestParam(value = "openid") String openid ,  @RequestParam(value = "orderId") String orderId){
        System.out.println("---------   openid : ----------- " +openid);
        ModelAndView mav = new ModelAndView();
        buyerService.cancelOrder(openid  , orderId);
        mav.setViewName("redirect:/buyer/mine");
        return mav;
//        return ResultVOUtil.success();
    }


    /**
     * 删除订单
     * @param orderId 订单号
     * @param openid
     * @return
     */
    @GetMapping(value = "/delete")
    public ModelAndView deleteOrder(@RequestParam(value = "orderId") String orderId , @RequestParam(value = "openid") String openid ){
        ModelAndView mav =  new ModelAndView();
        try{
            orderService.delete(orderId ,openid);
        }catch (Exception e){
            log.error("【订单删除】订单删除失败 e = {} " , e.getMessage());
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        mav.setViewName("redirect:/buyer/mine");
        return mav;
    }



}
