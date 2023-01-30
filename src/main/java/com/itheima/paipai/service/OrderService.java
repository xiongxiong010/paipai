package com.itheima.paipai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.paipai.bean.Orders;


public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);
}
