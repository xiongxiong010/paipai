package com.itheima.paipai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.paipai.bean.Dish;
import com.itheima.paipai.dto.DishDto;

import java.util.List;

public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息，同时更新对应的口味信息
    public void updateWithFlavor(DishDto dishDto);

    //删除菜品信息，同时删除对应的口味信息
    public void deleteWithFlavor(List<Long> ids);

    //修改菜品状态
    public void updataStatus(Long[] ids,int i);


}
