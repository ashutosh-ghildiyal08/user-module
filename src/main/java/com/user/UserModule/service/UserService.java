package com.user.UserModule.service;

import com.user.UserModule.dao.UserDao;
import com.user.UserModule.publisher.AddUserPublisher;
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
        AddUserPublisher addUserPublisher = objectTranslator.translate(responseUserDto, AddUserPublisher.class);
        String message = objectTranslator.writeValueAsString(addUserPublisher, "add");
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

        String message = "action=delete," +"id=" +userId;
        publishService.publishMessage(message, "user");
    }

}
