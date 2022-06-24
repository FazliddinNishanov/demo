package com.example.demo.exseption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExsistExseption extends RuntimeException {
    public UserExsistExseption(String message) {
        super(message);
    }
}
