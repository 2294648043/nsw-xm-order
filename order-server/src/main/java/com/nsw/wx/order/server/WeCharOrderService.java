package com.nsw.wx.order.server;

import com.github.pagehelper.PageInfo;
import com.nsw.wx.order.pojo.WeCharOrdeDetail;
import com.nsw.wx.order.pojo.WeCharOrder;


import java.util.List;

public interface WeCharOrderService {
    /**
     * 根据订单编号删除订单信息
     * @param orderNo
     * @return int
     */
    int deleteByPrimaryKey(String orderNo);
    /**
     * author Wu_kong
     * @param page
     * @param pageSize
     * @return List
     */
    PageInfo<WeCharOrder> pageSelect(int page, int pageSize);
    /**
     * @return list
     */
    List<WeCharOrder> finaAll();

    /**
     * @param record
     * @return
     */
    int insert(WeCharOrder record);

    /**
     * 通过id查询订单信息
     * @param id
     * @return
     */
    WeCharOrder finaAllByid(int id);

    /**
     * 通过订单编号修改订单的状态
     * @param weCharOrder
     * @return int
     */
    int updateByPrimary(WeCharOrder weCharOrder);

    /**
     * 根据用户id和发送来的订单状态请求查询信息
     * @param userid
     * @param orderstate
     * @return
     */
    List<WeCharOrder> orderdetailuserid(int userid);
}
