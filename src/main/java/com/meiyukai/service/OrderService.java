package com.meiyukai.service;

import com.meiyukai.domain.OrderMaster;
import com.meiyukai.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    /**  创建订单 **/
    OrderDTO createOrder(OrderDTO orderDTO);

    /**  查询单个订单 **/
    OrderDTO findOne( String orderID);

    /**  根据openId 查询订单列表 **/
    Page<OrderDTO> findOrderList(String buyerOpenid , Pageable pageable);


    /**  查询所有的订单列表 **/
    Page<OrderDTO> findAllOrderList(Pageable pageable);


    /**根据openid 查询所有的 orderDTO**/
    List<OrderDTO> findAllByOpenid(String openid);



    /**  取消订单 **/
    OrderDTO cancel(OrderDTO orderDTO);

    /**  完结订单 **/
    OrderDTO finish(OrderDTO orderDTO);

    /**  支付订单 **/
    OrderDTO paid(OrderDTO orderDTO);

    /**   获取订单详情  **/
    List<String> getOrderName(OrderDTO orderDTO);

    /**
     * 根据openid 查询所有的OrderMaster
     */

    List<OrderMaster> findAll(String openid );


    /**
     * 根据openid 和订单号删除订单
     */

    void delete(String orderId  , String openid);



}
