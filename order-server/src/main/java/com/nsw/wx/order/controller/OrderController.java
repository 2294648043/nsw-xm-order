package com.nsw.wx.order.controller;

import com.nsw.wx.order.VO.ResultVO;
import com.nsw.wx.order.com.nsw.wx.order.dto.OrderDTO;
import com.nsw.wx.order.converter.OrderForm2OrderDTOConverter;
import com.nsw.wx.order.enums.ResultEnum;
import com.nsw.wx.order.exception.OrderException;
import com.nsw.wx.order.form.OrderForm;
import com.nsw.wx.order.server.OrderService;
import com.nsw.wx.order.util.JsonMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 张维维
 * 2018-10-19 16:36
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1. 参数检验
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
//        if (bindingResult.hasErrors()){
//            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
//            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
//                    bindingResult.getFieldError().getDefaultMessage());
//        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
//        Map<String, String> map = new HashMap<>();
//        map.put("orderId", result.getOrderId());
//        return ResultVOUtil.success(map);
          return null;


    }
//
//    @PostMapping("/create")
//    public ResultVO<Map<String, String>> create(@RequestBody String json_str,
//                                                BindingResult bindingResult, HttpServletResponse response) {
//        response.setHeader("Access-Control-Allow-Origin","*");
//// if (bindingResult.hasErrors()){
////            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
////            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
////                    bindingResult.getFieldError().getDefaultMessage());
////        }
//        // orderForm -> orderDTO
////        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
////        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
////            log.error("【创建订单】购物车信息为空");
////            throw new OrderException(ResultEnum.CART_EMPTY);
////        }
//        OrderDTO resultx=new JsonMap().string2Obj(json_str,new OrderDTO().getClass());
//        System.out.println("+++++++++++++++++"+resultx);
//        OrderDTO result = orderService.create(resultx);
////        Map<String, String> map = new HashMap<>();
////        map.put("orderId", result.getOrderId());
////        return ResultVOUtil.success(map);
//        return null;
//
//
//    }
}

