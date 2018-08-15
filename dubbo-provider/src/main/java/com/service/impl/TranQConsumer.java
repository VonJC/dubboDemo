package com.service.impl;

import com.util.SpringContextUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/9
 **/
@Service
public class TranQConsumer extends Thread implements MessageListener{
    private Connection conn = null;
    private Destination destination = null;
    private Session session = null;
    private String msgStr;

    private boolean flag = false;

    public void run() {
        if(flag == false){
            receive();
        }
    }

    public void receive() {
        ConnectionFactory factory = null;
        Connection conn = null;
        try {
//            final  ApplicationContext context = new ClassPathXmlApplicationContext("classpath:activemq.xml");
            final  ApplicationContext context = SpringContextUtils.getWebApplicationContext();
            factory = (ActiveMQConnectionFactory) context.getBean("targetConnectionFactory");
            conn = factory.createConnection();
            conn.start();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = (Destination) context.getBean("destination");
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(this);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMessage(Message message) {
        try {
            //handler message
            TextMessage tm = (TextMessage) message;
            System.out.println("TranQConsumer receive message: " + tm.getText());
            setMsgStr(tm.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        TranQConsumer tranConsumer = new TranQConsumer();
        tranConsumer.start();
    }

    public String getMsgStr(){
        return msgStr;
    }

    void setMsgStr(String msgStr){
        this.msgStr = msgStr;
    }


}
