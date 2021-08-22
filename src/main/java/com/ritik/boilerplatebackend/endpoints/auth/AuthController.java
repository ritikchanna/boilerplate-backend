package com.ritik.boilerplatebackend.endpoints.auth;


import com.ritik.boilerplatebackend.endpoints.auth.pojo.request.LogInRequest;
import com.ritik.boilerplatebackend.endpoints.auth.pojo.request.SignUpRequest;
import com.ritik.boilerplatebackend.endpoints.auth.pojo.response.LoginResponse;
import com.ritik.boilerplatebackend.endpoints.auth.utils.JwtUtils;
import com.ritik.boilerplatebackend.endpoints.base.pojo.BaseResponse;
import com.ritik.boilerplatebackend.endpoints.user.pojo.User;
import com.ritik.boilerplatebackend.endpoints.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LogInRequest logInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logInRequest.getUsername(), logInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new LoginResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles, BaseResponse.SUCCESS, logInRequest.getRequestId());
    }

    @PostMapping("/signup")
    public BaseResponse<String> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new BaseResponse<>(BaseResponse.FAILURE, "Username is already taken",signUpRequest.getRequestId());
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new BaseResponse<>(BaseResponse.FAILURE, "Email is already in use",signUpRequest.getRequestId());
        }


        User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstName(),
                signUpRequest.getLastName(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

            userRepository.save(user);
        return new BaseResponse<>(BaseResponse.SUCCESS, "User registered successfully",signUpRequest.getRequestId());
    }
}