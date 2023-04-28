package com.user.UserModule.service;

import com.user.UserModule.entity.Address;
import com.user.UserModule.entity.User;
import com.user.UserModule.repository.AddressRepository;
import com.user.UserModule.repository.UserRepository;
import com.user.UserModule.request.AddUserRequest;
import com.user.UserModule.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(AddUserRequest addUserRequest) {
        Address address = new Address();
        address.setAddress_line1(addUserRequest.getAddress_line1());
        address.setAddress_line2(addUserRequest.getAddress_line2());
        addressRepository.save(address);
        User user = new User();
        user.setUsername(addUserRequest.getUsername());
        user.setPassword(addUserRequest.getPassword());
        user.setAddress(address);
        userRepository.save(user);

    }


    public User getSingleUser(Integer userId) {
        return userRepository.findById(userId).get();
    }


    public void updateUser(UpdateUserRequest updateUserRequest) {
        Address address = updateUserRequest.getAddress();
        address.setAddress_line1(updateUserRequest.getAddress_line1());
        address.setAddress_line2(updateUserRequest.getAddress_line2());
        addressRepository.save(address);
        User user = new User();
        user.setUsername(updateUserRequest.getUsername());
        user.setPassword(updateUserRequest.getPassword());
        user.setAddress(address);
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

}
