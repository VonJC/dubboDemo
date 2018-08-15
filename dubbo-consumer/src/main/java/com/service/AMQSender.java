package com.service;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/9
 **/
public class AMQSender {
    public static void sendWithAuto(ApplicationContext context, String msg) {
        ActiveMQConnectionFactory factory = null;
        Connection conn = null;
        Destination destination = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            destination = (Destination) context.getBean("destination");
            factory = (ActiveMQConnectionFactory) context.getBean("targetConnectionFactory");
            conn = factory.createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(destination);
            Message message = session.createTextMessage(msg);
            producer.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                producer.close();
                producer = null;
            } catch (Exception e) {
            }
            try {
                session.close();
                session = null;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            conn.stop();
        } catch (Exception e) {
        }
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:activemq.xml");
        sendWithAuto(context, "HAHA I AM GROOT!s");
    }
}
