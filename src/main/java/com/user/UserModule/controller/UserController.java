package com.user.UserModule.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.UserModule.entity.UserEntity;
import com.user.UserModule.publisher.UserPublisher;
import com.user.UserModule.request.AddUserRequest;
import com.user.UserModule.request.UpdateUserRequest;
import com.user.UserModule.response.UserDto;
import com.user.UserModule.response.UserResponse;
import com.user.UserModule.service.AddressService;
import com.user.UserModule.service.UserService;
import com.user.UserModule.translator.ObjectTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @Autowired
    ObjectTranslator objectTranslator;

   /* @GetMapping("/publish/all")
    public ResponseEntity<String> publishAllUser() throws JsonProcessingException {
        List<UserPublisher> list = userService.getAllUsersForPublisher();
        return new ResponseEntity<>("message sent", HttpStatus.OK);
    }
*/
    /*@GetMapping("/publish/one")
    public ResponseEntity<String> publishOneUser() throws JsonProcessingException {
        UserPublisher user = userService.getOneUserForPublisher();
        String arrayToJson = mapper.writeValueAsString(user);
        String message = arrayToJson.toString();
        jmsTemplate.convertAndSend(queue, message);
        System.out.println("message Sent");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }*/


   @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
       List<UserDto> users = userService.getAllUsers();
       List<UserResponse> usersResponses = new ArrayList<>();
       for (UserDto user : users) {
           UserResponse userResponse = objectTranslator.translate(user, UserResponse.class);
           usersResponses.add(userResponse);
       }

        return new ResponseEntity<>(usersResponses, HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<UserResponse>  addUser(@Valid @RequestBody AddUserRequest addUserRequest) {
        UserDto userDto = objectTranslator.translate(addUserRequest, UserDto.class);
        UserDto responseUserDto = userService.addUser(userDto);
        UserResponse userResponse = objectTranslator.translate(responseUserDto, UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getSingleUser(@PathVariable Integer userId) {
        UserDto responseUserDto = userService.getSingleUser(userId);
        UserResponse userResponse = objectTranslator.translate(responseUserDto, UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {

        userService.deleteUser(userId);

        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        UserDto userDto = objectTranslator.translate(updateUserRequest, UserDto.class);
        UserDto responseUserDto = userService.updateUser(userDto);
        UserResponse userResponse = objectTranslator.translate(responseUserDto, UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
