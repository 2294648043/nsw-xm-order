package com.nsw.wx.order.server.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsw.wx.order.mapper.WeCharOrdeDetailMapper;
import com.nsw.wx.order.mapper.WeCharOrderMapper;
import com.nsw.wx.order.pojo.WeCharOrder;
import com.nsw.wx.order.server.WeCharOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeCharOrderServiceImpl implements WeCharOrderService {
    @Autowired
    private WeCharOrderMapper weCharOrderMapper;
    @Autowired
    private WeCharOrdeDetailMapper weCharOrdeDetailMapper;

    @Override
    public int deleteByPrimaryKey(String orderNo) {
        weCharOrdeDetailMapper.deleteByPrimaryKey(orderNo);
        return weCharOrderMapper.deleteByPrimaryKey(orderNo);
    }

    @Override
    public PageInfo<WeCharOrder> pageSelect(int page, int pageSize) {
        //pageHelper帮我们生成分页语句
        PageHelper.startPage(page,pageSize);
        List<WeCharOrder> findlist = weCharOrderMapper.finaAll();
        PageInfo<WeCharOrder> pageInfoUserList =  new PageInfo<WeCharOrder>(findlist);
        return pageInfoUserList;
    }

    @Override
    public List<WeCharOrder> finaAll() {

        return weCharOrderMapper.finaAll();
    }

    @Override
    public int insert(WeCharOrder record) {
        int count = weCharOrderMapper.insert(record);
        return count;
    }

    /**
     * 实现通过id查询订单信息 finaAllByid()
     * @param id
     * @return
     */
    @Override
    public WeCharOrder finaAllByid(int id) {
        return weCharOrderMapper.finaAllByid(id);
    }

    /**
     * 修改信息
     * @param weCharOrder
     * @return int
     */
    @Override
    public int updateByPrimary(WeCharOrder weCharOrder) {
        return weCharOrderMapper.updateByPrimary(weCharOrder);
    }


    @Override
    public List<WeCharOrder> orderdetailuserid(int userid) {
        return weCharOrderMapper.orderdetailuserid(userid);
    }
}
