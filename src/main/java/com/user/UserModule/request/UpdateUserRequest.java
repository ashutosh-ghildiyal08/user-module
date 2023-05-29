package com.user.UserModule.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateUserRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    private String id;
    @Email
    @NotNull@NotBlank@NotEmpty
    private String email;
    @NotNull@NotBlank@NotEmpty
    private String firstName;
    @NotNull@NotBlank@NotEmpty
    private String lastName;
    @NotNull@NotBlank@NotEmpty
    private String addressLine1;
    @NotNull@NotBlank@NotEmpty
    private String addressLine2;
    @NotNull@NotBlank@NotEmpty
    private String city;
    @NotNull@NotBlank@NotEmpty
    private String country;

}
