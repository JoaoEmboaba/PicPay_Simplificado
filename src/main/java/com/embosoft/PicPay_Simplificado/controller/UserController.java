package com.embosoft.PicPay_Simplificado.controller;

import com.embosoft.PicPay_Simplificado.DTO.UserDTO;
import com.embosoft.PicPay_Simplificado.domain.user.User;
import com.embosoft.PicPay_Simplificado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newuser = userService.createUser(user);
        return new ResponseEntity<>(newuser, CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, OK);
    }
}
