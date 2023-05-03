package com.user.UserModule.controller;

import com.user.UserModule.entity.User;
import com.user.UserModule.request.AddUserRequest;
import com.user.UserModule.request.UpdateUserRequest;
import com.user.UserModule.service.AddressService;
import com.user.UserModule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;


   @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users= (List<User>) userService.getAllUsers();
        return users;
    }
    @PostMapping("/users")
    public List<User> addUser(@RequestBody AddUserRequest addUserRequest) {
        System.out.println(addUserRequest);
        userService.addUser(addUserRequest);
        List<User> users= (List<User>) userService.getAllUsers();
        return users;
    }
    @GetMapping("/users/{userId}")
    public User getSingleUser(@PathVariable Integer userId) {
        System.out.println(userId);
        User user = userService.getSingleUser(userId);
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public List<User> deleteUser(@PathVariable Integer userId) {
        System.out.println(userId);
        userService.deleteUser(userId);
        List<User> users= (List<User>) userService.getAllUsers();
        return users;
    }

    @PutMapping("/users")
    public List<User> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        System.out.println(updateUserRequest);
        userService.updateUser(updateUserRequest);
        List<User> users= (List<User>) userService.getAllUsers();
        return users;
    }
}
