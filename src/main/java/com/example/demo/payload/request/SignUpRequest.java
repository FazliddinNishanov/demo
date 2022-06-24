package com.example.demo.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */

@Data
public class SignUpRequest {

    @Email(message = "It should email format")
    @NotBlank(message = "User email is required")
    private String email;
    @NotEmpty(message = "Please enter your Name")
    private String firstname;
    @NotEmpty(message = "Please enter your lastName")
    private String lastname;
    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Password is reuired")
    @Size(min = 3)
    private String password;
    private String confirmPassword;
}
