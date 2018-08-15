package com.service.impl;

import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/9
 **/
@Service
public class MqServiceImpl extends TranQConsumer{

    public void onMessage(Message message){
        try{
            TextMessage tm = (TextMessage) message;
            System.out.println("TranQConsumer receive message11: " + tm.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
