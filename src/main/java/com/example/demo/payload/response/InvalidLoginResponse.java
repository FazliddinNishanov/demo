package com.example.demo.payload.response;

import lombok.Getter;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */

@Getter
public class InvalidLoginResponse {

    private String username;
    private String password;

    public InvalidLoginResponse(){
        this.username = "Invalid Username";
        this.password = "Invalid password";
    }
}
