package com.itheima.paipai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.itheima.paipai.bean.OrderDetail;
import com.itheima.paipai.mapper.OrderDetailMapper;
import com.itheima.paipai.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}