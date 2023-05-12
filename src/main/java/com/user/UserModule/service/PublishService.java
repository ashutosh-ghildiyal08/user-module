package com.user.UserModule.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
@Service
public class PublishService {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @Autowired
    ObjectMapper mapper;

    public void publishMessage(String message, String queue){
        jmsTemplate.convertAndSend(queue, message);

    }

}
