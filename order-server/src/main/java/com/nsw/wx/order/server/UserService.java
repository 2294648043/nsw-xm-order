package com.nsw.wx.order.server;

import com.github.pagehelper.PageInfo;
import com.nsw.wx.order.pojo.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    PageInfo<User> pageSelect(int page, int pageSize);


    List<User>getAll();

    int delectTest(int user_id);

}
