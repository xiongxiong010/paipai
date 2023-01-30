package com.itheima.paipai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.itheima.paipai.bean.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}
