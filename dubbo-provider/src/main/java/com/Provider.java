package com;

import com.service.impl.MqServiceImpl;
import com.service.impl.TranQConsumer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/9
 **/
public class Provider {
    public static void main(String[] args) {
        try{
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
            System.out.println(context.getDisplayName() + ": here");
            context.start();//
            System.out.println("服务已经启动...");
            System.in.read();


//            System.out.println("mq provider");
////            tranConsumer.onMessage(Message);
//            System.out.println(tranConsumer.getMsgStr());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
