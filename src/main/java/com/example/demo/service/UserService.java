package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entitiy.User;
import com.example.demo.entitiy.enums.ERole;
import com.example.demo.exseption.UserExsistExseption;
import com.example.demo.payload.request.SignUpRequest;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser (SignUpRequest userIn){
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userIn.getPassword()));
        user.getRole().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving User {} ",  userIn.getEmail());
            return userRepository.save(user);
        }catch (Exception e){
            LOG.error("Registration Error .{}", e.getMessage());
            throw new UserExsistExseption("the User " + user.getUsername()+ " already exsist" );
        }
    }

    public User updateUser(UserDTO userDTO, Principal principal){
            User user = getUserByPrincipal(principal);
            user.setName(userDTO.getFirstname());
            user.setLastname(userDTO.getLastname());
            user.setBio(userDTO.getBio());
            return userRepository.save(user);
    }

    public User getCurrentuser(Principal principal){
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found: " + username));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new UsernameNotFoundException("User not found") );
    }
}
