package com.mq.rabbitmq.rabbitmqtest;

/**
 * Created by fuyongyi on 2017-02-20.
 */

import java.util.Date;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveTest {

    private final static String QUEUE_NAME = "ftpAgent";
    private final static String userName = "root";
    private final static String password = "root";
    private final static String virtualHost = "/";
    private final static int portNumber = 5672;
    private final static String hostName = "master";
    private final static String host = "192.168.12.136";

    public static void main(String[] argv) throws java.io.IOException,
            java.lang.InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
//      factory.setHost("192.168.174.160");
        factory.setUsername(userName);
        factory.setPassword(password);
//      factory.setVirtualHost(virtualHost);
        factory.setHost(host);
        factory.setPort(portNumber);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//      channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        Date nowTime = new Date();

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("RecieveTime: " + nowTime);
            System.out.println(" [x] Received '" + message + "'");
        }

    }
}
