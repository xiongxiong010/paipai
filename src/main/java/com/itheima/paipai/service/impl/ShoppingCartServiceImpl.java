package com.itheima.paipai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.itheima.paipai.bean.ShoppingCart;
import com.itheima.paipai.mapper.ShoppingCartMapper;
import com.itheima.paipai.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
