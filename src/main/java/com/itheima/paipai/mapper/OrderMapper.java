package com.itheima.paipai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.itheima.paipai.bean.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}