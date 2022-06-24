package com.example.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {
    private boolean success;
    private String message;
}
