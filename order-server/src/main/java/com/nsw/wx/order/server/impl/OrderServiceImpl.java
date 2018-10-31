package com.nsw.wx.order.server.impl;

import com.netflix.discovery.converters.Auto;
import com.nsw.wx.order.com.nsw.wx.order.dto.OrderDTO;
import com.nsw.wx.order.controller.ProductClient;
import com.nsw.wx.order.enums.OrderStatusEnum;
import com.nsw.wx.order.enums.PayStatusEnum;
import com.nsw.wx.order.mapper.WeCharOrdeDetailMapper;
import com.nsw.wx.order.mapper.WeCharOrderMapper;
import com.nsw.wx.order.pojo.WeCharOrdeDetail;
import com.nsw.wx.order.pojo.WeCharOrder;
import com.nsw.wx.order.server.OrderService;
import com.nsw.wx.order.util.KeyUtil;
import common.DecreaseStockInput;
import common.WeChatProductOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 张维维
 * 2018-10-19
 */
@Service
public class OrderServiceImpl implements OrderService {



@Autowired
private WeCharOrdeDetailMapper weCharOrdeDetailMapper;
@Autowired
private WeCharOrderMapper weCharOrderMapper;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
       //查询商品信息(调用商品服务) 获取商品信息
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(WeCharOrdeDetail::getProductid)
                .collect(Collectors.toList());
        //得到商品信息
        System.out.println();
        List<WeChatProductOutput> productInfoList = productClient.listForOrder(productIdList);

       //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);


        for (WeCharOrdeDetail weCharOrdeDetail: orderDTO.getOrderDetailList()) {
            for (WeChatProductOutput productInfo: productInfoList) {
                if (productInfo.getId().equals(weCharOrdeDetail.getProductid())) {
                    //单价*数量
                    orderAmout = productInfo.getPrice()
                    .multiply(new BigDecimal(weCharOrdeDetail.getNum()))
                    .add(orderAmout);
                    weCharOrdeDetail.setUserprice(orderAmout);
                    BeanUtils.copyProperties(productInfo, weCharOrdeDetail);
                    weCharOrdeDetail.setOrderid("13213");
                    weCharOrdeDetail.setProductname(productInfo.getTitle());
                    weCharOrdeDetail.setProductprice(productInfo.getPrice());
                    weCharOrdeDetail.setPricesum(orderAmout);
                    weCharOrdeDetail.setEnable(true);
                    weCharOrdeDetail.setInputtime(new Date());
                    weCharOrdeDetail.setCarttype(null);
                    weCharOrdeDetail.setSkuid(12);
                    BigDecimal aDouble =new BigDecimal(198);
                    weCharOrdeDetail.setGroupbuyprice(aDouble);
                    weCharOrdeDetail.setOffertype(1);
                    weCharOrdeDetail.setDeposit(aDouble);
                    weCharOrdeDetail.setRent(aDouble);
                    weCharOrdeDetail.setDay(320);
                    weCharOrdeDetail.setStatus(1212);
                    weCharOrdeDetail.setUserid(12);
                    weCharOrdeDetail.setOid("132123");
                    //订单详情入库
                    weCharOrdeDetailMapper.insert(weCharOrdeDetail);
                }
            }
        }


       //扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductid(), e.getNum()))
                .collect(Collectors.toList());
        System.out.println(decreaseStockInputList+"----------------------");
        productClient.decreaseStock(decreaseStockInputList);

        //订单入库
        WeCharOrder orderMaster = new WeCharOrder();
        orderDTO.setOrderno("sdkfjh212");
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderno("121313131");
        orderMaster.setTotal(orderAmout);
        orderMaster.setOrderstate(OrderStatusEnum.NEW.getCode());
        orderMaster.setPaystate(PayStatusEnum.WAIT.getCode());
        weCharOrderMapper.insert(orderMaster);
        return orderDTO;
    }
}
