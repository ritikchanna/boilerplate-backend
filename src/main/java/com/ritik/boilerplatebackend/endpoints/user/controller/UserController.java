package com.ritik.boilerplatebackend.endpoints.user.controller;


import com.ritik.boilerplatebackend.endpoints.base.pojo.BaseResponse;
import com.ritik.boilerplatebackend.endpoints.user.pojo.User;
import com.ritik.boilerplatebackend.endpoints.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ritik.boilerplatebackend.endpoints.user.pojo.User.Role;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping(path = "/")
    public @ResponseBody
    BaseResponse<Iterable<User>> getAllUsers() {
        return new BaseResponse<>(BaseResponse.SUCCESS,userRepository.findAll(),null);
    }


    @PostMapping(path = "/register")
    public @ResponseBody
    Iterable<User> registerUser() {
        return userRepository.findAll();

    }
}
