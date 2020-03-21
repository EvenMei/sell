package com.meiyukai.controller;

import com.meiyukai.domain.OrderDetail;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/seller/order")
@Slf4j
public class SellOrderController {

    @Resource(name = "orderService")
    private OrderService orderService;




    /**
     *
     * @param page  从第几页开始 （下标从零开始）
     * @param size （每页显示多少条记录）
     * @return
     */
    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page ,
                                                    @RequestParam(value = "size" ,defaultValue = "10") Integer size,
                             Map<String , Object> map){
        ModelAndView mav  =  new ModelAndView( );
        PageRequest pageRequest = PageRequest.of(page-1, size);
        Page<OrderDTO> orderDTOPage = orderService.findAllOrderList(pageRequest);

//        log.info("【查询所有的订单 】 orderDTOList ={}" , orderDTOPage.getContent());

        map.put("orderDTOPage" , orderDTOPage);
        map.put("currentPage" , page);
        map.put("size"  , size);
        mav.addAllObjects(map);
        mav.setViewName("order/list");

        return mav;
    }


    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping(value = "/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") String orderId ,
                                                            Map<String , Object> map){
        ModelAndView mav = new ModelAndView();
        OrderDTO orderDTO =null;
        try{
             orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
            mav.setViewName("redirect:/seller/order/list");
        }catch(Exception e){
            log.error("【卖家端取消订单】 订单不存在 orderId = {}" , orderId);
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/order/list");
            mav.addAllObjects(map);
            mav.setViewName("common/error");
            return mav;
        }
        map.put("msg" , "取消成功！");
        map.put("url" , "/sell/seller/order/list");
        mav.addAllObjects(map);
        mav.setViewName("common/success");
        return mav;
    }


    /**
     * 查看商品个详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping(value = "/detail")
    public ModelAndView detail(String orderId , Map<String , Object > map){
        ModelAndView mav = new ModelAndView();
        OrderDTO orderDTO = orderService.findOne(orderId);
        map.put("orderDTO"  , orderDTO);
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

        try{
            if (orderDetailList == null){
                log.error("【详情查询】商品详情查询失败  orderDTO ={}" , orderDTO   );
                throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
            }
        }catch(Exception e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/order/list");
            mav.setViewName("common/error");
            return mav;
        }

        map.put("orderDetailList" , orderDetailList);
        mav.addAllObjects(map);

        mav.setViewName("order/detail");
        return mav;
    }


    @GetMapping(value = "/finish")
    public ModelAndView finish(@RequestParam(value = "orderId") String orderId , Map<String , Object> map){

        ModelAndView mav = new ModelAndView();
        OrderDTO orderDTO = orderService.findOne(orderId);


        try{
            if (orderDTO == null){
                log.error("【卖家端完结订单】 orderID = {}  " , orderId);
                throw  new SellException(ResultEnum.ORDER_NOT_EXISTS);
            }

            orderService.finish(orderDTO);
        }catch(Exception e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/order/list");
            mav.addAllObjects(map);
            mav.setViewName("common/error");
        }


        map.put("msg" ,"完结订单成功！");
        map.put("url" , "/sell/seller/order/detail?orderId="+orderId);
        mav.setViewName("common/success");
        mav.addAllObjects(map);
        return mav;

    }









}
