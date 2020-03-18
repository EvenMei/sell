package com.meiyukai.domain;

import com.meiyukai.enums.OrderStatusEnum;
import com.meiyukai.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "order_master")
@DynamicUpdate
public class OrderMaster implements Serializable {
    @Column(name = "order_id")
    @Id
    private String orderId;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_phone")
    private String buyerPhone;

    @Column(name = "buyer_address")
    private String buyerAddress;

    @Column(name = "buyer_openid")
    private String buyerOpenid;

    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    @Column(name = "order_status")
    private Integer orderStatus = OrderStatusEnum.NEW.getCode(); //默认是 0 新下单状态;

    @Column(name = "pay_status")
    private Integer payStatus = PayStatusEnum.WAIT.getCode(); // 默认是0 等待支付


    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /*@Transient
    private List<OrderDetail> orderDetailList;*/



}
