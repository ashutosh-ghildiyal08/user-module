package com.user.UserModule.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
