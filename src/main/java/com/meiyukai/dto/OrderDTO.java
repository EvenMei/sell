package com.meiyukai.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiyukai.domain.OrderDetail;
import com.meiyukai.enums.OrderStatusEnum;
import com.meiyukai.enums.PayStatusEnum;
import com.meiyukai.utils.EnumUtils;
import com.meiyukai.utils.TransactionStatusUtils;
import com.meiyukai.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * dto = data transfer object
 * (order_master)
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)  //次用法已经被废弃掉了
//@JsonInclude(JsonInclude.Include.NON_NULL) // 空的数据不往前端传送
public class OrderDTO implements Serializable {

        private String orderId;

        private String buyerName;

        private String buyerPhone;

        private String buyerAddress;

        private String buyerOpenid;

        private BigDecimal orderAmount;


        private Integer orderStatus  = OrderStatusEnum.NEW.getCode();

        private Integer payStatus =  PayStatusEnum.WAIT.getCode();

        @JsonSerialize(using = Date2LongSerializer.class)
        private Date createTime;

        @JsonSerialize(using = Date2LongSerializer.class)
        private Date updateTime;


        private List<OrderDetail> orderDetailList ;/*= new ArrayList<>();*/  // 非必需的值 因此不需要初始化


        @JsonIgnore
        public String getOrderStatusEnum(){
                return EnumUtils.getByCode(orderStatus , OrderStatusEnum.class).getMsg();
        }


        @JsonIgnore
        public String getPayStatusEnum(){
                return EnumUtils.getByCode(payStatus , PayStatusEnum.class).getMsg();
        }

        @JsonIgnore
        public String getTransactionStatus(){
                return TransactionStatusUtils.getTransactionStatusEnum(payStatus , orderStatus).getMsg();
        }

        @JsonIgnore
        public Integer getTransactionCode(){
                return TransactionStatusUtils.getTransactionStatusEnum(payStatus , orderStatus).getCode();
        }

        @JsonIgnore
        public String getCreateTime(){
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
        }

        @JsonIgnore
        public Integer getTotalDetailAmount(){
                return orderDetailList.size();
        }







}
