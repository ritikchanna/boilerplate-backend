package com.ritik.boilerplatebackend.endpoints.debug.controller;

import com.ritik.boilerplatebackend.endpoints.auth.UserDetailsImpl;
import com.ritik.boilerplatebackend.endpoints.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ConditionalOnExpression("${application.debug:false}")
@RequestMapping("/debug")
public class DebugController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/reset")
    public @ResponseBody
    String reset() {
        userRepository.deleteAll();
        return "OK";
    }

    @GetMapping(path = "/up")
    public @ResponseBody
    String isAvailable() {
        return "Yes";
    }


    @GetMapping(path = "/user")
    public @ResponseBody
    UserDetailsImpl currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }

}
