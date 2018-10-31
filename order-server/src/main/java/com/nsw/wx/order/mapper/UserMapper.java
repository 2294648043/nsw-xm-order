package com.nsw.wx.order.mapper;


import com.nsw.wx.order.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);
    List<User> queryAll();

    int delectTest(int user_id);


}