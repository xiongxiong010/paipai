package com.itheima.paipai.dto;

import com.itheima.paipai.bean.Dish;
import com.itheima.paipai.bean.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
