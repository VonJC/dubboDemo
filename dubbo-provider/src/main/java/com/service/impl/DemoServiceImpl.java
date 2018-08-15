package com.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.service.DemoService;

/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/9
 **/
@Service
public class DemoServiceImpl implements DemoService {

    public String printAnything(String s) {
        System.out.println("I am provider!!");
        System.out.println("param is " +  s);
        return "succ";
    }
}
