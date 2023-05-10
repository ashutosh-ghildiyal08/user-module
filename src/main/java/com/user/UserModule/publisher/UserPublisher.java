package com.user.UserModule.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPublisher {
    private Integer id;
    private String email;


    @Override
    public String toString() {
        return "[" + id + " " + email + "]";
    }
}
