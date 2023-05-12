package com.user.UserModule.service;

import com.user.UserModule.dao.UserDao;
import com.user.UserModule.entity.UserEntity;
import com.user.UserModule.publisher.UserPublisher;
import com.user.UserModule.response.UserDto;
import com.user.UserModule.translator.ObjectTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    ObjectTranslator objectTranslator;
    @Autowired
    UserDao userDao;

    @Autowired
    PublishService publishService;


    public List<UserDto> getAllUsers(){

        return userDao.getAllUsers();
    }

    public UserDto addUser(UserDto userDto) {
        //validation
        UserDto responseUserDto = userDao.addUser(userDto);
        //publish
        UserPublisher userPublisher = objectTranslator.translate(responseUserDto, UserPublisher.class);
        String message = objectTranslator.writeValueAsString(userPublisher, "addUser");
        publishService.publishMessage(message, "user");
        return responseUserDto;

    }

    public UserDto getSingleUser(Integer userId) {
        return userDao.getSingleUser(userId);
    }


    public UserDto updateUser(UserDto userDto) {
        UserDto responseUserDto =  userDao.updateUser(userDto);
        return responseUserDto;
    }

    public void deleteUser(Integer userId) {
        userDao.deleteUser(userId);
    }

    public List<UserPublisher> getAllUsersForPublisher() {
        return userDao.getAllUsersForPublisher();
    }

    public UserPublisher getOneUserForPublisher() {

        return null;
    }
}
