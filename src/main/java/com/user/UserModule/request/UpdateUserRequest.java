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
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$", message = "password should have uppercase and lowercase and special character.")
    private String password;
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
