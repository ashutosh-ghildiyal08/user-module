package com.user.UserModule.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.UserModule.publisher.UserPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MessageConsumer {
    @Autowired
    ObjectMapper mapper;
   /* @JmsListener(destination = "demo")
    public void consume(String message) throws JsonProcessingException {

        //2. Convert JSON to List of Person objects
        //Define Custom Type reference for List<Person> type
        TypeReference<List<UserPublisher>> mapType = new TypeReference<>() {};
        List<UserPublisher> jsonToPersonList = mapper.readValue(message, mapType);
        for (UserPublisher userPublisher: jsonToPersonList) {
            System.out.println(userPublisher);
        }

    }*/
}
