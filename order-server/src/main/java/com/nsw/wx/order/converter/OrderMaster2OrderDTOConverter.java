package com.nsw.wx.order.converter;

import com.nsw.wx.order.com.nsw.wx.order.dto.OrderDTO;
//import com.nsw.wx.order.dto.OrderDTO;
import com.nsw.wx.order.pojo.WeCharOrder;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 张维维
 * 2018-10-23 20:02
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(WeCharOrder orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<WeCharOrder> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
