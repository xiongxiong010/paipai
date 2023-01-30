package com.itheima.paipai.dto;

import com.itheima.paipai.bean.OrderDetail;
import com.itheima.paipai.bean.Orders;
import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;

}
