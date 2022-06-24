package com.example.demo.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */

@Data
public class LoginRequset {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;

}
