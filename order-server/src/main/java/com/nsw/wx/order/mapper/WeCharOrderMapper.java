package com.nsw.wx.order.mapper;



import com.nsw.wx.order.pojo.WeCharOrder;

import java.util.List;
import java.util.Optional;

public interface WeCharOrderMapper {
    /**
     * @return list
     */
    List<WeCharOrder> findList();

    /**
     *
     * @param record
     * @return int
     */
    int insert(WeCharOrder record);

    /**
     * 根据订单编号删除订单信息
     * @param orderNo
     * @return int
     */
    int deleteByPrimaryKey(String orderNo);

    /**
     * 通过id查询订单信息
     * @param id
     * @return
     */
   WeCharOrder finaAllByid(int id);

    /**
     * 通过订单编号修改订单的状态
     * @param
     * @return int
     */
    int updateByPrimary(WeCharOrder weCharOrder);

    int updateOrderStatus(WeCharOrder weCharOrder);



}