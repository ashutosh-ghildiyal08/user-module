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

    /*@PostMapping("/users")
    public String addUser(int id, String username, String password,
                           String address_line1, String address_line2, Model model) {
        System.out.println(id+ username+ password+address_line1+ address_line2);
        Address address = new Address();
        address.setAddress_line1(address_line1); address.setAddress_line2(address_line2);
        System.out.println(address);
        addressRepository.save(address);

        User user = new User();
        user.setId(id); user.setUsername(username); user.setPassword(password); user.setAddress(address);
        System.out.println(user);
        userRepository.save(user);
        List<User> users= (List<User>) userRepository.findAll();
        model.addAttribute("allUsers", users);
        return "home";
    }*/
    @PostMapping("/users")
    public List<User> addUser(AddUserRequest addUserRequest) {
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

    /*@PutMapping("/users")
    public String updateUser(@RequestBody UserRequest userRequest) {
        System.out.println(userRequest);
        User user = userRepository.findById(id).get();
        Address address = user.getAddress();

        address.setAddress_line1(address_line1); address.setAddress_line2(address_line2);

        user.setUsername(username); user.setPassword(password); user.setAddress(address);
        System.out.println(user);
        userRepository.save(user);
        return "User Updated";
    }*/
    @PutMapping("/users")
    public List<User> updateUser(UpdateUserRequest updateUserRequest) {
        System.out.println(updateUserRequest);
        userService.updateUser(updateUserRequest);
        List<User> users= (List<User>) userService.getAllUsers();
        return users;
    }
}
