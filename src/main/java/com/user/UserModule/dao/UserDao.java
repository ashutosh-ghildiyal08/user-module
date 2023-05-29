package com.user.UserModule.dao;

import com.user.UserModule.entity.AddressEntity;
import com.user.UserModule.entity.UserEntity;
import com.user.UserModule.repository.AddressRepository;
import com.user.UserModule.repository.UserRepository;
import com.user.UserModule.response.UserDto;
import com.user.UserModule.translator.ObjectTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDao {

    @Autowired
    ObjectTranslator objectTranslator;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    public UserDto findUserByEmailAndId(UserDto userDto) {
        UserDto savedUserDto = null;
        UserEntity savedUserEntity = userRepository.findByEmailAndPassword(
                userDto.getEmail(), userDto.getPassword());
        if(savedUserEntity==null){
            return savedUserDto;
        }
        savedUserDto = objectTranslator.userEntityToUserDtoConverter(savedUserEntity);

        return savedUserDto;
    }

    public List<UserDto> getAllUsers(){

        List<UserEntity> usersEntity = userRepository.findAll();
        usersEntity.forEach(userEntity -> System.out.println(userEntity.stringConcat()));
        List<UserDto> usersDto = new ArrayList<>();
        for (UserEntity user : usersEntity) {
            UserDto userDto = objectTranslator.userEntityToUserDtoConverter(user);
            usersDto.add(userDto);
        }

        return usersDto;
    }

    public UserDto addUser(UserDto userDto) {

        AddressEntity addressEntity = AddressEntity.builder()
                .addressLine1(userDto.getAddressLine1())
                .addressLine2(userDto.getAddressLine2())
                .city(userDto.getCity())
                .country(userDto.getCountry())
                .build();

        addressRepository.save(addressEntity);

        UserEntity userEntity = objectTranslator.translate(userDto, UserEntity.class);
        userEntity.setAddress(addressEntity);

        UserEntity savedUserEntity = userRepository.save(userEntity);
        UserDto savedUserDto = objectTranslator.userEntityToUserDtoConverter(savedUserEntity);
        return savedUserDto;

    }

    public UserDto getSingleUser(Integer userId) {
        UserEntity user = userRepository.findById(userId).get();
        UserDto userDto = objectTranslator.userEntityToUserDtoConverter(user);
        return userDto;
    }


    public UserDto updateUser(UserDto userDto) {

        int userId = userDto.getId();
        UserEntity previousDetails = userRepository.findById(userId).get();

        AddressEntity address = previousDetails.getAddress();
        address.setAddressLine1(userDto.getAddressLine1());
        address.setAddressLine2(userDto.getAddressLine2());
        address.setCity(userDto.getCity());
        address.setCountry(userDto.getCountry());
        System.out.println(address);
        addressRepository.save(address);

        previousDetails.setEmail(userDto.getEmail());

        previousDetails.setFirstName(userDto.getFirstName());
        previousDetails.setLastName(userDto.getLastName());
        previousDetails.setAddress(address);
        userRepository.save(previousDetails);
        System.out.println(previousDetails.stringConcat());
        UserDto updatedUser = objectTranslator.userEntityToUserDtoConverter(previousDetails);
        System.out.println(updatedUser);
        return updatedUser;
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }


}
