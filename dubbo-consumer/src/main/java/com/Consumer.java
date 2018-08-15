package com;

import com.service.AMQSender;
import com.service.DemoService;
import com.service.PayService;
import com.vo.Bill;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/9
 **/
public class Consumer {
    public static void main(String[] args) {
        //测试常规服务
        ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        DemoService demoService = (DemoService) context.getBean(DemoService.class);
        System.out.println("consumer");
        System.out.println(demoService.printAnything("I am consumer!!!"));

        System.out.println("pay start");
        PayService payService = context.getBean(PayService.class);
        Bill bill = new Bill();
        Date date = new Date();
        bill.setOrderNo(Long.toString(date.getTime()));
        bill.setType("01");
        bill.setAmount(new BigDecimal("10000"));
        System.out.println(payService.pay(bill));
        System.out.println("pay done");

        for(int i = 0; i < 100; i ++){
            AMQSender.sendWithAuto(context, "HAHA I AM GROOT!");
        }

    }
}
