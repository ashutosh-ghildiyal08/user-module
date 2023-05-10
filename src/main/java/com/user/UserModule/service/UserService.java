package com.user.UserModule.service;

import com.user.UserModule.entity.Address;
import com.user.UserModule.entity.User;
import com.user.UserModule.publisher.UserPublisher;
import com.user.UserModule.repository.AddressRepository;
import com.user.UserModule.repository.UserRepository;
import com.user.UserModule.request.AddUserRequest;
import com.user.UserModule.request.UpdateUserRequest;
import com.user.UserModule.translator.ObjectTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    ObjectTranslator objectTranslator;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(AddUserRequest addUserRequest) {

        Address address = Address.builder()
                .address_line1(addUserRequest.getAddress_line1())
                .address_line2(addUserRequest.getAddress_line2())
                .city(addUserRequest.getCity())
                .country(addUserRequest.getCountry())
                .build();

        addressRepository.save(address);

        User user = objectTranslator.translate(addUserRequest,User.class);
        user.setAddress(address);

        userRepository.save(user);

    }

    public User getSingleUser(Integer userId) {
        return userRepository.findById(userId).get();
    }


    public void updateUser(UpdateUserRequest updateUserRequest) {
        int userId = Integer.parseInt(updateUserRequest.getId());
        User user = userRepository.findById(userId).get();

        Address address = user.getAddress();
        address.setAddress_line1(updateUserRequest.getAddress_line1());
        address.setAddress_line2(updateUserRequest.getAddress_line2());
        address.setCity(updateUserRequest.getCity());
        address.setCountry(updateUserRequest.getCountry());

        addressRepository.save(address);

        user.setEmail(updateUserRequest.getEmail());
        user.setPassword(updateUserRequest.getPassword());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setAddress(address);
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public List<UserPublisher> getAllUsersForPublisher() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserPublisher> userPublisherList = new ArrayList<UserPublisher>() ;
        for (User user: users) {
            UserPublisher userPublisher = objectTranslator.translate(user, UserPublisher.class);
            userPublisherList.add(userPublisher);
        }
        return userPublisherList;
    }
}
