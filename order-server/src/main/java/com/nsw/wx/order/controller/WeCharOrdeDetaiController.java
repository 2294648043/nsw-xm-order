package com.nsw.wx.order.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.nsw.wx.order.pojo.WeCharOrdeDetail;
import com.nsw.wx.order.server.WeCharOrdeDetailService;
import com.nsw.wx.order.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/detail")
public class WeCharOrdeDetaiController {

    @Autowired
    private WeCharOrdeDetailService weCharOrdeDetailService;


    @GetMapping("/insert")
    public int insert(HttpServletResponse response){
    WeCharOrdeDetail weCharOrdeDetail = new WeCharOrdeDetail();
//        weCharOrdeDetail.setUserid(55);
//        weCharOrdeDetail.setOid("p37201707161908420000000003");
//        weCharOrdeDetail.setProductid(2);
//        weCharOrdeDetail.setProductname("皮皮畅");
//        BigDecimal bg1=new BigDecimal("1.00");
//        weCharOrdeDetail.setProductprice(bg1);
//        weCharOrdeDetail.setUserprice(bg1);
//        weCharOrdeDetail.setNum(1);
//        weCharOrdeDetail.setPricesum(bg1);
//        weCharOrdeDetail.setEnable(true);
//        weCharOrdeDetail.setInputtime(new Date());
//        weCharOrdeDetail.setOrderid(50);
//        weCharOrdeDetail.setIntegral(11111);
//        weCharOrdeDetail.setCarttype("p");
//        weCharOrdeDetail.setSkuid(12154);
//        weCharOrdeDetail.setGroupbuyprice(new BigDecimal("1888.00"));
//        weCharOrdeDetail.setShortdesc(" ");
//        weCharOrdeDetail.setAttribute(" ");
//        weCharOrdeDetail.setAttributekeyvalue(" ");
//        System.out.println("===============》"+weCharOrdeDetail.getUserid());
        return weCharOrdeDetailService.insert(weCharOrdeDetail);
    }
    @GetMapping("/findAll")
    public Object findAll(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("limit"));
        System.out.println(page);
        System.out.println(pageSize);
        PageInfo<WeCharOrdeDetail> pageInfoList = weCharOrdeDetailService.pageSelect(page, pageSize);
        List<WeCharOrdeDetail> list = pageInfoList.getList();
        long count=pageInfoList.getTotal();
        String json = JSONArray.toJSONString(list);
        return JsonData.buildSuccess(count,list);
    }

    @GetMapping("/del")
    public int del(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return weCharOrdeDetailService.deleteByPrimaryKey("c415_201803161620520002");
    }

    @RequestMapping("/update")
    public int Update(HttpServletResponse response, @RequestParam("oid") String oid,@RequestParam("status") int status){
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println("=========================oid"+oid+"+++++++++++++++++++++++"+status);
        return weCharOrdeDetailService.updateByPrimaryOid(oid,status);
    }

}
