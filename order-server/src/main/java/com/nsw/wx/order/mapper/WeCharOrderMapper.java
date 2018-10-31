package com.nsw.wx.order.mapper;



import com.nsw.wx.order.pojo.WeCharOrdeDetail;
import com.nsw.wx.order.pojo.WeCharOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeCharOrderMapper {
    public static void main(String[] args) {

    }
    /**
     * @return list
     */
    List<WeCharOrder> finaAll();

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
     * @param weCharOrder
     * @return int
     */
    int updateByPrimary(WeCharOrder weCharOrder);

    /**
     * 根据用户id查询订单信息
     * @param userid
     * @return
     */
    List<WeCharOrder> orderdetailuserid(@Param("userid") int userid);

}