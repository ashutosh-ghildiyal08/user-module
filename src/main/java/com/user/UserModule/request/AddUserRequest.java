package com.user.UserModule.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUserRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address_line1;
    private String address_line2;
    private String city;
    private String country;
}
