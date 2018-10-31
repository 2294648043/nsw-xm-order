package com.nsw.wx.order.controller;


import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.nsw.wx.order.pojo.WeCharOrdeDetail;
import com.nsw.wx.order.pojo.WeCharOrder;
import com.nsw.wx.order.server.WeCharOrdeDetailService;
import com.nsw.wx.order.server.WeCharOrderService;
import com.nsw.wx.order.util.JsonData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class WeCharOrdeController {
    @Autowired
    private WeCharOrderService weCharOrderService;

    @Autowired
    private WeCharOrdeDetailService weCharOrdeDetailService;

    @GetMapping("/test")
    public  BigDecimal test(){

       System.out.println(new BigDecimal("0.00"));
        return new BigDecimal("0.00") ;
    }
    @RequestMapping("/insert")
    public int insert(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        WeCharOrder order = weCharOrderService.finaAllByid(23);
        System.out.println("----------------------->"+order.getOrderno());
        order.setOrderno("c415_201803161620520002");
        System.out.println("----------------------->"+order.getOrderno());

        System.out.println("============="+order.getOrderno());
        return weCharOrderService.insert(order);
    }
    @GetMapping("/findAll")
    public Object findAll(HttpServletRequest request, HttpServletResponse response){
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("limit"));
        System.out.println(page);
        System.out.println(pageSize);
        PageInfo<WeCharOrder> pageInfoList = weCharOrderService.pageSelect(page, pageSize);
        List<WeCharOrder> list = pageInfoList.getList();
        long count=pageInfoList.getTotal();
        String json = JSONArray.toJSONString(list);
        return JsonData.buildSuccess(count,list);
    }

    @RequestMapping("/del")
    public int Del(HttpServletResponse response,@RequestParam("orderNo") String orderNo){
        response.setHeader("Access-Control-Allow-Origin","*");
        return weCharOrderService.deleteByPrimaryKey(orderNo);
    }

    @GetMapping("/finaAllByid")
    public WeCharOrder finaAllByid(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return weCharOrderService.finaAllByid(23);
    }

    @RequestMapping("/update")
    public int Update(HttpServletResponse response,@RequestBody()WeCharOrder weCharOrder){
        response.setHeader("Access-Control-Allow-Origin","*");
        return weCharOrderService.updateByPrimary(weCharOrder);
    }

    /**
     * 根据用户id和订单状态查询订
     * 单编号再根据编号查询订单详情
     * @param response
     * @param userid
     * @param
     * @return
     */
    @RequestMapping("orderdetailuserid")
    public Object orderdetailuserid(HttpServletResponse response,
                                    @RequestParam("userid") int userid) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<WeCharOrder> weCharOrder = weCharOrderService.orderdetailuserid(userid);
        //2. 获取weCharOrder的订单编号
        List<String> categoryOIDList = weCharOrder.stream()
                .map(WeCharOrder::getOrderno)
                .collect(Collectors.toList());
        System.out.println(categoryOIDList);
        List<WeCharOrdeDetail> weCharOrdeDetail = (List<WeCharOrdeDetail>) weCharOrdeDetailService.selectoid(categoryOIDList);
        System.out.println(weCharOrdeDetail);
        List<WeCharOrdeDetail> weCharOrdeDetailX = new ArrayList<>();
        for (WeCharOrdeDetail productInfo : weCharOrdeDetail) {
            if (productInfo.getStatus() == 0) {
                productInfo.setOrderstatus("新订单");
            } else if (productInfo.getStatus() == 1) {
                productInfo.setOrderstatus("完结");
            } else if (productInfo.getStatus() == 2) {
                productInfo.setOrderstatus("待取消");
            } else if (productInfo.getStatus() == 3) {
                productInfo.setOrderstatus("已取消");
            }
            WeCharOrdeDetail weCharOrdeDetail1 = new WeCharOrdeDetail();
            BeanUtils.copyProperties(productInfo, weCharOrdeDetail1);
            weCharOrdeDetailX.add(weCharOrdeDetail1);
        }
        System.out.println("+++++++++++++++++++++++++++"+weCharOrdeDetailX);
        return weCharOrdeDetail;
    }

}
