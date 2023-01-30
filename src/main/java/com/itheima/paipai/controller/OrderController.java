package com.itheima.paipai.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.paipai.bean.Dish;
import com.itheima.paipai.bean.OrderDetail;
import com.itheima.paipai.bean.Orders;
import com.itheima.paipai.bean.ShoppingCart;
import com.itheima.paipai.common.BaseContext;
import com.itheima.paipai.common.R;
import com.itheima.paipai.dto.DishDto;
import com.itheima.paipai.dto.OrdersDto;
import com.itheima.paipai.service.OrderDetailService;
import com.itheima.paipai.service.OrderService;
import com.itheima.paipai.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }

    /**
     * 管理端订单分页查询
     * @param page
     * @param pageSize
     * @param number
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, Long number, @DateTimeFormat String beginTime, @DateTimeFormat String endTime){

        log.info("页数：{}",page + "页码：{}",pageSize + "起始时间：{}",beginTime + "终止时间：{}",endTime);

        //分页构造器
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();

        if (number != null ){
            //添加过滤条件
            queryWrapper.eq(Orders::getNumber,number);
        }

        if (beginTime != null && endTime != null){
            //添加过滤条件
            queryWrapper.between(Orders::getOrderTime,beginTime,endTime);
        }
        //添加排序条件
        queryWrapper.orderByDesc(Orders::getOrderTime);
        //分页查询
        orderService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 管理端状态修改
     * @param orders
     * @return
     */
    @PutMapping
    public R<String> changeStatus(@RequestBody Orders orders){

        orderService.updateById(orders);

        return R.success("修改成功");
    }

    /**
     * 用户端订单分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    public R<Page> page(int page, int pageSize){

        log.info("页数：{}",page + "页码：{}",pageSize);

        //获取当前用户id
        Long userId = BaseContext.getCurrentId();

        //分别构造两个空的分页构造器对象
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        Page<OrdersDto> ordersDtoPage = new Page<>(page, pageSize);

        //条件构造器（pageInfo对应的条件构造器）
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        /*给pageInfo对应的条件构造器加筛选条件，条件为“从orders数据库中取一条订单数据
         并且这条订单数据的userId属性的值要和当前用户的id相匹配”*/
        queryWrapper.eq(userId != null, Orders::getUserId, userId);
        //按时间降序排序
        queryWrapper.orderByDesc(Orders::getOrderTime);
        //这行代码一旦执行完毕，分页构造器对象pageInfo将会有一条订单数据，不在为空
        orderService.page(pageInfo, queryWrapper);

        BeanUtils.copyProperties(pageInfo, ordersDtoPage, "records");
        //这行代码的作用是，从分页构造器对象pageInfo中取出已经赋值的records的值，
        List<OrdersDto> list = pageInfo.getRecords().stream().map((item) -> {
            //创建一个新的容器，用来收取records数据中各个属性相对应的值
            OrdersDto ordersDto = new OrdersDto();
            //除了OrderDetails这个属性暂时为空，其他的属性都被赋值了
            BeanUtils.copyProperties(item, ordersDto);

            //获取orderId,然后根据这个id，去orderDetail表中查数据
            Long orderId = item.getId();
            //条件构造器
            LambdaQueryWrapper<OrderDetail> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(OrderDetail::getOrderId, orderId);
            List<OrderDetail> details = orderDetailService.list(wrapper);

            //之后set一下属性，为什么要set，因为这个属性的值之前并没有从records中拿到
            ordersDto.setOrderDetails(details);
            return ordersDto;
        }).collect(Collectors.toList());

        ordersDtoPage.setRecords(list);
        //日志输出看一下
        log.info("list:{}", list);
        return R.success(ordersDtoPage);
    }

    @PostMapping("/again")
    public R<String> againSubmit(@RequestBody Orders orders){
        // 获得订单ID
        Long ordersId = orders.getId();
        //根据订单id去订单详情中拿到相对应的菜品信息
        LambdaQueryWrapper<OrderDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderDetail::getOrderId,ordersId);
        List<OrderDetail> orderDetails = orderDetailService.list(lambdaQueryWrapper);

        //获取用户id，待会需要set操作
        Long userId = BaseContext.getCurrentId();

        List<ShoppingCart> shoppingCarts = orderDetails.stream().map((item) -> {
            ShoppingCart shoppingCart = new ShoppingCart();
            //Copy对应属性值
            BeanUtils.copyProperties(item,shoppingCart);
            //设置一下userId
            shoppingCart.setUserId(userId);
            //设置一下创建时间为当前时间
            shoppingCart.setCreateTime(LocalDateTime.now());
            return shoppingCart;
        }).collect(Collectors.toList());

        //加入购物车
        shoppingCartService.saveBatch(shoppingCarts);

        return R.success("操作成功");
    }
}