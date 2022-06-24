package com.example.demo.facade;

import com.example.demo.dto.UserDTO;
import com.example.demo.entitiy.User;
import org.springframework.stereotype.Component;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 23.06.2022
 */

@Component
public class UserFacade {

    public UserDTO userToUserDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());
        return userDTO;
    }
}
