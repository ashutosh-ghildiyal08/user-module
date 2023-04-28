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
    private Integer userId;
    private String username;
    private String password;
    private Address address;
    private String address_line1;
    private String address_line2;
}
