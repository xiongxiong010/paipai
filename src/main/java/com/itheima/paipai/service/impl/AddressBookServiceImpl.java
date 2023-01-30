package com.itheima.paipai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.itheima.paipai.bean.AddressBook;
import com.itheima.paipai.mapper.AddressBookMapper;
import com.itheima.paipai.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
