package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */

@Data
public class CommentDTO {

    private Long id;
    @NotEmpty
    private String message;
    private String username;

}
