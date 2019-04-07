package com.xuecheng.test.rabbitmq.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestProducer02 {

    //定义交换机
    private final static String EXCHANGE_FANOUT_INFORM = "EXCHANGE_FANOUT_INFORM";
    //定义队列
    private final static String QUEUE_INFORM_SMS="QUEUE_INFORM_SMS";
    private final static String QUEUE_INFORM_EMAIL="QUEUE_INFORM_EMAIL";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPassword("guest");
        factory.setUsername("guest");
        factory.setHost("106.12.211.16");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * 定义交换机
         * FANOUT：发布订阅模式
         */
        channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
    }
}
