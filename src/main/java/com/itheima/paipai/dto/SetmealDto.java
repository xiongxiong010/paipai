package com.itheima.paipai.dto;

import com.itheima.paipai.bean.Setmeal;
import com.itheima.paipai.bean.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
