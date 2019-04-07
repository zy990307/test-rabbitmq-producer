package com.xuecheng.test.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestProducer01 {
    private static final String TEST_QUEUE = "TEST_QUEUE";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("106.12.211.16");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/"); //rabbitmq默认虚拟机名称为“/”，虚拟机相当于一个独立的mq服务器

        Connection connection = null;

        //创建与rabbitmq服务的tcp连接
        connection = factory.newConnection();
        //创建与Exchange的通道，每个连接可以创建多个通道，每个通道代表一个会话任务
        Channel channel = connection.createChannel();

        /**
         * 声明队列，如果Rabbit中没有此队列将自动创建
         * param1:队列名称
         * param2:是否持久化
         * param3:队列是否独占此连接
         * param4:队列不再使用时是否自动删除此队列
         * param5:队列参数
         */
        channel.queueDeclare(TEST_QUEUE,true,false,false,null);
        /**
         * 消息发布方法
         * param1：Exchange的名称，如果没有指定，则使用Default Exchange
         * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列
         * param3:消息包含的属性
         * param4：消息体
         */
        channel.basicPublish("",TEST_QUEUE,null,("测试队列"+System.currentTimeMillis()).getBytes());
        System.out.println("send to mq");
        channel.close();
        connection.close();
    }
}
