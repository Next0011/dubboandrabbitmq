package com.xin.producer;

/**
 * Created by fuyongyi on 2017-02-20.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageProducer {

    private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Object message) {
        logger.info("to send message:{}", message);
        amqpTemplate.convertAndSend("queueTestKey", message);
    }
}
