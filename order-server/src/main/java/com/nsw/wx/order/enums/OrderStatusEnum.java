package com.nsw.wx.order.enums;

import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-12-10 16:18
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    DCANCEL(2, "待取消"),
    CANCEL(3, "已取消"),
    ;
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
