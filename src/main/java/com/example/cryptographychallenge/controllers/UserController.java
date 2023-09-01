package com.example.cryptographychallenge.controllers;

import com.example.cryptographychallenge.Dtos.UserDTO;
import com.example.cryptographychallenge.entites.UserEntity;
import com.example.cryptographychallenge.repositories.UserRepository;
import com.example.cryptographychallenge.services.CryptographyService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CryptographyService cryptographyService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){
        UserEntity userEntity = new UserEntity(userDTO);
        cryptographyService.cryptographyUser(userEntity);
        userRepository.save(userEntity);
        return ResponseEntity.ok().build();
    }
}
