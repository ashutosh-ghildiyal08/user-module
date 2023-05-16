package com.user.UserModule.service;

import com.user.UserModule.dao.UserDao;
import com.user.UserModule.publisher.UserEventMessage;
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
        UserEventMessage userEventMessage = objectTranslator.translate(responseUserDto, UserEventMessage.class);
        String message = objectTranslator.translatePayloadAsString(userEventMessage, "CREATE");
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

        String message = objectTranslator.translatePayloadAsString(userId, "DELETE");
        publishService.publishMessage(message, "user");
    }

}
