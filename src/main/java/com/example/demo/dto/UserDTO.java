package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */

@Data
public class UserDTO {

    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String username;
    private String bio;

}
