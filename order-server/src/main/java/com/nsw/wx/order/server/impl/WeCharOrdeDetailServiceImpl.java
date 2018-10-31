package com.nsw.wx.order.server.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsw.wx.order.mapper.WeCharOrdeDetailMapper;
import com.nsw.wx.order.pojo.WeCharOrdeDetail;
import com.nsw.wx.order.server.WeCharOrdeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeCharOrdeDetailServiceImpl implements WeCharOrdeDetailService {

    @Autowired
    private WeCharOrdeDetailMapper weCharOrdeDetailMapper;

    @Override
    public PageInfo<WeCharOrdeDetail> pageSelect(int page, int pageSize) {
            //pageHelper帮我们生成分页语句
            PageHelper.startPage(page,pageSize);
            List<WeCharOrdeDetail> findlist = weCharOrdeDetailMapper.finaAll();
            PageInfo<WeCharOrdeDetail> pageInfoUserList =  new PageInfo<WeCharOrdeDetail>(findlist);
            return pageInfoUserList;
    }
    @Override
    public List<WeCharOrdeDetail> finaAll() {

        return weCharOrdeDetailMapper.finaAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String id) {

        return  weCharOrdeDetailMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改订单状态
     * @param
     * @return
     */
    @Override
    public int updateByPrimaryOid(String oid,int status) {
        WeCharOrdeDetail order=new WeCharOrdeDetail();
        order.setOid(oid);
        order.setStatus(status);
        System.out.println("+++++++++++++++++++++++++++"+oid+"++++++++++++++++++"+status);
        return weCharOrdeDetailMapper.updateByPrimaryOid(order);
    }

    /**
     * author Wu_kong
     * @param record
     * @return count
     */
    @Override
    public int insert(WeCharOrdeDetail record) {
        int count = weCharOrdeDetailMapper.insert(record);
        return count;
    }

    @Override
    public List<WeCharOrdeDetail> selectoid(List<String> oid) {
        return weCharOrdeDetailMapper.selectoid(oid);
    }
}
