package com.user.UserModule.translator;

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

    public <T, R> R translate(T source, Class<R> targetType) {
        return modelMapper.map(source, targetType);
    }


}
