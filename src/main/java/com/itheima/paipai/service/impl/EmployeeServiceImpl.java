package com.itheima.paipai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.paipai.bean.Employee;
import com.itheima.paipai.mapper.EmployeeMapper;
import com.itheima.paipai.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{
}
