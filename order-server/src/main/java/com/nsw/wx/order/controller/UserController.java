package com.nsw.wx.order.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.nsw.wx.order.pojo.User;
import com.nsw.wx.order.server.UserService;
import com.nsw.wx.order.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping(value = "/add")
    public  int nb(@RequestBody User user){
    int id = userService.addUser(user);
    return id;
    }


    @RequestMapping("/index")
    @ResponseBody
    public Object index(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        response.setHeader("Access-Control-Allow-Origin", "*");
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("limit"));
        System.out.println(page);
        System.out.println(pageSize);

        PageInfo<User> pageInfoList = userService.pageSelect(page, pageSize);

        List<User> list = pageInfoList.getList();
        long count=pageInfoList.getTotal();

        String json = JSONArray.toJSONString(list);
        return JsonData.buildSuccess(count,list);
    }



    @RequestMapping("/index1")
    @ResponseBody
    public Object list1(HttpServletRequest request, HttpServletResponse response){
       response.setHeader("Access-Control-Allow-Origin","*");
        int user_id = Integer.parseInt(request.getParameter("user_id"));

      return userService.delectTest(user_id);
    }

}
