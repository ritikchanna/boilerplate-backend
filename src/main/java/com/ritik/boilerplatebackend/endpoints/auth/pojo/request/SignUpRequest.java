package com.ritik.boilerplatebackend.endpoints.auth.pojo.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
