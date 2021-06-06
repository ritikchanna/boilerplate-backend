package com.ritik.boilerplatebackend.endpoints.user.controller;


import com.ritik.boilerplatebackend.endpoints.user.pojo.User;
import com.ritik.boilerplatebackend.endpoints.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping(path = "/")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();

    }
}
