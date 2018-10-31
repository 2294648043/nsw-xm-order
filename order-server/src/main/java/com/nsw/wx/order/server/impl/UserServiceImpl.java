package com.nsw.wx.order.server.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsw.wx.order.mapper.UserMapper;
import com.nsw.wx.order.pojo.User;
import com.nsw.wx.order.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }
    public PageInfo<User> pageSelect(int page, int pageSize) {
        //pageHelper帮我们生成分页语句
        PageHelper.startPage(page,pageSize);
        List<User> findlist = userMapper.queryAll();
        PageInfo<User> pageInfoUserList =  new PageInfo<User>(findlist);
        return pageInfoUserList;

    }

    @Override
    public List<User> getAll() {
        return userMapper.queryAll();
    }

    @Override
    public int delectTest(int user_id) {
        return userMapper.delectTest(user_id);
    }

}
