package com.user.UserModule.request;

import com.user.UserModule.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateUserRequest {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address_line1;
    private String address_line2;
    private String city;
    private String country;
}
