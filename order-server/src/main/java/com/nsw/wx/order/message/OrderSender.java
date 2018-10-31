package com.nsw.wx.order.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nsw.wx.order.dto.OrderDTO;
import com.nsw.wx.order.enums.OrderStatusEnum;
import com.nsw.wx.order.enums.ResultEnum;
import com.nsw.wx.order.exception.OrderException;
import com.nsw.wx.order.mapper.WeCharOrderMapper;
import com.nsw.wx.order.pojo.WeCharOrdeDetail;
import com.nsw.wx.order.pojo.WeCharOrder;
import com.nsw.wx.order.redis.RedisService;
import com.nsw.wx.order.util.FastJsonConvertUtil;
import common.DecreaseStockInput;
import common.WeChatProductOutput;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * 订单发送者
 * @author 张维维
 * date: 2018/10/29/029 20:11
 */
@Component
public class OrderSender {
    //自动注入RabbitTemplate模板类
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private WeCharOrderMapper weCharOrderMapper;

    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {

            if (!ack) {

                System.out.println("异常处理");
            }
        }
        }
        ;

        //发送消息方法调用: 构建自定义对象消息
        public void sendOrder(OrderMessage orderMessage) throws Exception {

            rabbitTemplate.setConfirmCallback(confirmCallback);
            //消息唯一ID

            rabbitTemplate.convertAndSend("order-exchange10", "order.ABCDE", FastJsonConvertUtil.convertObjectToJSON(orderMessage));
        }

}
