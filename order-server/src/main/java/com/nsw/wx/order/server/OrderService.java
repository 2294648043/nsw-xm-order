package com.nsw.wx.order.server;

import com.nsw.wx.order.com.nsw.wx.order.dto.OrderDTO;

/**
 * Created by 廖师兄
 * 2017-12-10 16:39
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 购物车
     * @param orderDTO
     * @return
     */
    //OrderDTO shoppingcart(OrderDTO orderDTO);
}
