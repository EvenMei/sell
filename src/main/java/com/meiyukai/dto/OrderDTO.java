package com.meiyukai.dto;

import com.meiyukai.domain.OrderDetail;
import com.meiyukai.enums.OrderStatusEnum;
import com.meiyukai.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * dto = data transfer object
 * (order_master)
 */
@Data
public class OrderDTO implements Serializable {

        private String orderId;

        private String buyerName;

        private String buyerPhone;

        private String buyerAddress;

        private String buyerOpenid;

        private BigDecimal orderAmount;

        private Integer orderStatus  = OrderStatusEnum.NEW.getCode();

        private Integer payStatus =  PayStatusEnum.WAIT.getCode();

        private Date createTime;

        private Date updateTime;

    private List<OrderDetail> orderDetailList;





}
