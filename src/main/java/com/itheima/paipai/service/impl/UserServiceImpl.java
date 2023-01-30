package com.itheima.paipai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.itheima.paipai.bean.User;
import com.itheima.paipai.mapper.UserMapper;
import com.itheima.paipai.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
