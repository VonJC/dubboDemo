package com.service.impl;

/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/9
 **/
public class Listener {
    public Listener(){
        MqServiceImpl MqServiceImpl = new MqServiceImpl();
        MqServiceImpl.start();
    }
}
