package com.user.UserModule.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.UserModule.entity.User;
import com.user.UserModule.publisher.UserPublisher;
import com.user.UserModule.request.AddUserRequest;
import com.user.UserModule.request.UpdateUserRequest;
import com.user.UserModule.service.AddressService;
import com.user.UserModule.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;
    @Autowired
    ObjectMapper mapper;


    @GetMapping("/publish/all")
    public ResponseEntity<String> publishOneUser() throws JsonProcessingException {
        List<UserPublisher> list = userService.getAllUsersForPublisher();
        String arrayToJson = mapper.writeValueAsString(list);
        String message = arrayToJson.toString();
        jmsTemplate.convertAndSend(queue, message);
        System.out.println("message Sent");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


   @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users=  (List<User>) userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<List<User>>  addUser(@RequestBody AddUserRequest addUserRequest) {
        System.out.println(addUserRequest);
        userService.addUser(addUserRequest);
        List<User> users= (List<User>) userService.getAllUsers();
        /*mapper.writeValueAsString(u);*/
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable Integer userId) {
        System.out.println(userId);
        User user = userService.getSingleUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Integer userId) {
        System.out.println(userId);
        userService.deleteUser(userId);
        List<User> users= (List<User>) userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<List<User>> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        System.out.println(updateUserRequest);
        userService.updateUser(updateUserRequest);
        List<User> users= (List<User>) userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }
}
