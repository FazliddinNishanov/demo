package com.example.demo.dto;

import lombok.Data;

import java.util.Set;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */

@Data
public class PostDTO {

    private Long id;
    private String title;
    private String caption;
    private String location;
    private String username;
    private Integer likes;
    private Set<String> userLiked;
}
