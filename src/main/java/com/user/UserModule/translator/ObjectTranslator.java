package com.user.UserModule.translator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.UserModule.entity.AddressEntity;
import com.user.UserModule.entity.UserEntity;
import com.user.UserModule.response.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ObjectTranslator {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;

    public <T, R> R translate(T source, Class<R> targetType) {
        return modelMapper.map(source, targetType);
    }
    public String translatePayloadAsString(Object payload, String action)  {
        String finalPayloadMessage = "action=" +action +",";
        try {
            if(action=="DELETE")
                finalPayloadMessage += "id=" +objectMapper.writeValueAsString(payload);
            else
                finalPayloadMessage += objectMapper.writeValueAsString(payload);
            return finalPayloadMessage;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public UserDto userEntityToUserDtoConverter(UserEntity userEntity){
        UserDto userDto = translate(userEntity, UserDto.class);
        AddressEntity addressEntity = userEntity.getAddress();
        userDto.setAddressLine1(addressEntity.getAddressLine1());
        userDto.setAddressLine2(addressEntity.getAddressLine2());
        userDto.setCity(addressEntity.getCity());
        userDto.setCountry(addressEntity.getCountry());

        return userDto;
    }

}
