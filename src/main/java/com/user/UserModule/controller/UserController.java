package com.user.UserModule.controller;

import com.user.UserModule.request.AddUserRequest;
import com.user.UserModule.request.LoginRequest;
import com.user.UserModule.request.UpdateUserRequest;
import com.user.UserModule.response.UserDto;
import com.user.UserModule.response.UserResponse;
import com.user.UserModule.service.AddressService;
import com.user.UserModule.service.UserService;
import com.user.UserModule.translator.ObjectTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ObjectTranslator objectTranslator;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDto userDto = objectTranslator.translate(loginRequest, UserDto.class);
        UserDto responseUserDto = userService.login(userDto);
        if(responseUserDto == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            UserResponse userResponse = objectTranslator.translate(responseUserDto, UserResponse.class);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }

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
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Integer userId) {
        UserDto responseUserDto = userService.getSingleUser(userId);
        UserResponse userResponse = objectTranslator.translate(responseUserDto, UserResponse.class);
        userService.deleteUser(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        UserDto userDto = objectTranslator.translate(updateUserRequest, UserDto.class);
        UserDto responseUserDto = userService.updateUser(userDto);
        UserResponse userResponse = objectTranslator.translate(responseUserDto, UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
